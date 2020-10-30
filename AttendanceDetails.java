package app2.garrulousgirl.in.classattendance;

public class AttendanceDetails {
    int totalDays;
    int present;
    int absent;

    public AttendanceDetails() {
    }

    public AttendanceDetails(int totalDays, int present, int absent) {
        this.totalDays = 0;
        this.present = 0;
        this.absent = 0;
    }

    public int getTotalDays() {
        return totalDays;
    }

    public void setTotalDays(int totalDays) {
        this.totalDays = totalDays;
    }

    public int getPresent() {
        return present;
    }

    public void setPresent(int present) {
        this.present = present;
    }

    public int getAbsent() {
        return absent;
    }

    public void setAbsent(int absent) {
        this.absent = absent;
    }
}
