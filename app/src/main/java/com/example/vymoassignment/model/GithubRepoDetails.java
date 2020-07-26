package com.example.vymoassignment.model;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

import com.example.vymoassignment.BR;

public class GithubRepoDetails extends BaseObservable implements Parcelable {
    private String ownerName;
    private String repoName;

    public static final Creator<GithubRepoDetails> CREATOR = new Creator<GithubRepoDetails>() {
        @Override
        public GithubRepoDetails createFromParcel(Parcel in) {
            return new GithubRepoDetails(in);
        }

        @Override
        public GithubRepoDetails[] newArray(int size) {
            return new GithubRepoDetails[size];
        }
    };

    public GithubRepoDetails() {

    }

    protected GithubRepoDetails(Parcel in) {
        ownerName = in.readString();
        repoName = in.readString();
    }

    @Bindable
    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
        notifyPropertyChanged(BR.ownerName);
    }

    @Bindable
    public String getRepoName() {
        return repoName;
    }

    public void setRepoName(String repoName) {
        this.repoName = repoName;
        notifyPropertyChanged(BR.repoName);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(ownerName);
        parcel.writeString(repoName);
    }
}
