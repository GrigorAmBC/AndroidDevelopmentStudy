package com.example.vk;

import com.google.gson.annotations.SerializedName;

public class UserDetail {

    @SerializedName("first_name")
    private String firstName;
    @SerializedName("last_name")
    private String lastName;
    @SerializedName("birth_data")
    private String birthDate;
    @SerializedName("friend_count")
    private String friendCount;
    @SerializedName("b")
    private String audioCount;
    @SerializedName("")
    private String groupCount;
    @SerializedName("")
    private String status;
    @SerializedName("")
    private String lastSeenDate;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public String getFriendCount() {
        return friendCount;
    }

    public void setFriendCount(String friendCount) {
        this.friendCount = friendCount;
    }

    public String getAudioCount() {
        return audioCount;
    }

    public void setAudioCount(String audioCount) {
        this.audioCount = audioCount;
    }

    public String getGroupCount() {
        return groupCount;
    }

    public void setGroupCount(String groupCount) {
        this.groupCount = groupCount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getLastSeenDate() {
        return lastSeenDate;
    }

    public void setLastSeenDate(String lastSeenDate) {
        this.lastSeenDate = lastSeenDate;
    }
}
