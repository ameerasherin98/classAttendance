package app2.garrulousgirl.in.classattendance;

import com.google.gson.annotations.SerializedName;

public class PersonalInfo {
    @SerializedName("Name")
    String name;
    @SerializedName("Sem")
   int sem;
    @SerializedName("Dept")
    String dept;
    @SerializedName("NoOfSubs")
    int no_of_sub;
    @SerializedName("NoOfLabs")
    int no_of_lab;

    public PersonalInfo() {
    }

    public PersonalInfo(String name, int sem, String dept, int no_of_sub, int no_of_lab) {
        this.name = name;
        this.sem = sem;
        this.dept = dept;
        this.no_of_sub = no_of_sub;
        this.no_of_lab = no_of_lab;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSem() {
        return sem;
    }

    public void setSem(int sem) {
        this.sem = sem;
    }

    public String getDept() {
        return dept;
    }

    public void setDept(String dept) {
        this.dept = dept;
    }

    public int getNo_of_sub() {
        return no_of_sub;
    }

    public void setNo_of_sub(int no_of_sub) {
        this.no_of_sub = no_of_sub;
    }

    public int getNo_of_lab() {
        return no_of_lab;
    }

    public void setNo_of_lab(int no_of_lab) {
        this.no_of_lab = no_of_lab;
    }
}


