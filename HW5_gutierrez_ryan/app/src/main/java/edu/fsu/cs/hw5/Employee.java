package edu.fsu.cs.hw5;

/**
 ** Employee - Helper class for interacting with ContentProvider
 **/
public class Employee {


    private final String mEmployeeId;
    private final String mName;
    private final String mEmail;
    private final String mGender;
    private final String mPassword;
    private final String mDepartment;

    public Employee(String empid, String name, String email, String gender, String passwd,
                    String dept) {
        mEmployeeId = empid;
        mName = name;
        mEmail = email;
        mGender = gender;
        mPassword = passwd;
        mDepartment = dept;
    }

    public String getEmployeeId() {

        return mEmployeeId;
    }

    public String getName() {

        return mName;
    }

    public String getEmail() {

        return mEmail;
    }

    public String getGender() {

        return mGender;
    }

    public String getPassword() {

        return mPassword;
    }

    public String getDepartment() {

        return mDepartment;
    }
}
