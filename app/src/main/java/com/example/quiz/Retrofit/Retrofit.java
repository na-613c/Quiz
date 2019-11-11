package com.example.quiz.Retrofit;

/****************************************
 *      created by Shavlovskii Ivan     *
 *               08.11.2019             *
 ***************************************/


import android.annotation.SuppressLint;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class Retrofit {


    private List<List> allAnswers = new ArrayList<>();
    private Call<String> call;

    public List getAllAnswers(Integer index) {
        return allAnswers.get(index);
    }

    public List getListAllAnswers() {
        return allAnswers;
    }



    public void getResponse(String mod){

        retrofit2.Retrofit retrofit;

        retrofit = new retrofit2.Retrofit.Builder()
                .baseUrl("https://opentdb.com/")
                .addConverterFactory(ScalarsConverterFactory.create())
                .build();

        switch (mod){
            case "Easy":
                EasyInterface apiEasy = retrofit.create(EasyInterface.class);
                call = apiEasy.getString();
                break;

            case "Medium":
                MediumInterface apiMedium = retrofit.create(MediumInterface.class);
                call = apiMedium.getString();
                break;

            case "Hard":
                HardInterface apiHard = retrofit.create(HardInterface.class);
                call = apiHard.getString();
                break;
        }


        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                Log.i("Responsestring", response.body().toString());
                //Toast.makeText()
                if (response.isSuccessful()) {
                    if (response.body() != null) {


                        String jsonresponse = response.body().toString()
                                .replaceAll("&rsquo;", "’")
                                .replaceAll("\u0101", "a")
                                .replaceAll("\u014d", "o")
                                .replaceAll("&quot;", "“")
                                .replaceAll("&#039;", "'");

                        Log.i("onSuccess", jsonresponse);


                         writeTv(jsonresponse);

                    } else {
                        Log.i("onEmptyResponse", "Returned empty response");
                    }
                } else Log.i("________ERR", "DISCONECT");
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {

            }
        });


    }



    @SuppressLint("SetTextI18n")
    private void writeTv(String response){

        try {
            //getting the whole json object from the response
            JSONObject obj = new JSONObject(response);
            if(obj.optString("response_code").equals("0")){

                ArrayList<RetroModel> retroModelArrayList = new ArrayList<>();
                JSONArray dataArray  = obj.getJSONArray("results");

                for (int i = 0; i < dataArray.length(); i++) {

                    RetroModel retroModel = new RetroModel();
                    JSONObject dataobj = dataArray.getJSONObject(i);

                    /****************************************************************************/
                    int jsonArrayLenght = dataobj.getJSONArray("incorrect_answers").length();
                    Log.i("TAG___",""+jsonArrayLenght);

                    List<String> tempList = new ArrayList<String>(jsonArrayLenght);


                    for(int j =0; j < jsonArrayLenght;j++) {
                        tempList.add(dataobj.getJSONArray("incorrect_answers").getString(j));
                    }

                    /****************************************************************************/


                    retroModel.setCategory(dataobj.getString("category"));
                    retroModel.setType(dataobj.getString("type"));
                    retroModel.setDifficulty(dataobj.getString("difficulty"));
                    retroModel.setQuestion(dataobj.getString("question"));
                    retroModel.setCorrect_answer(dataobj.getString("correct_answer"));

                    /****************************************************************************/
                    retroModel.setIncorrectAnswers(tempList);
                    /****************************************************************************/


                    retroModelArrayList.add(retroModel);

                }

                for (int j = 0; j < retroModelArrayList.size(); j++){

                    List<String> answers = retroModelArrayList.get(j).getIncorrectAnswers();
                    answers.add(retroModelArrayList.get(j).getCorrect_answer());
                    answers.add(retroModelArrayList.get(j).getQuestion());

                    allAnswers.add(answers);
                    Log.d("__RETROFIT_","allAnswers full");

                }

            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }




}
