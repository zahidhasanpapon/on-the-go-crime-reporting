package com.example.firebase01;

public class User {

    private String password;
    private String organizationID;
    private String year;
    private String userType;

    public User(String p, String o, String y,String userType1){
        this.password =p;
        this.organizationID= o;
        this.year= y;
        this.userType= userType1;
    }
    public User(){

    }

    public  String OrgID(){
        return organizationID;
    }
    public  String getYear(){
        return year;
    }

    public String getPassword(){
        return password;
    }
    public String getUserType(){
        return userType;
    }





}
