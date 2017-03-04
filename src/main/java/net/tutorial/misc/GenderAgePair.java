package net.tutorial.misc;

/**
 * Created by pongpantola.
 */
public class GenderAgePair {
    private String gender;
    private String age;

    public GenderAgePair(String gender, String age){
        this.gender = gender;
        this.age = age;
    }

    public String getGender(){
        return gender;
    }

    public String getAge(){
        return age;
    }


    public void setGender(String gender){
        this.gender = gender;
    }

    public void setAge(String age){
        this.age = age;
    }


}