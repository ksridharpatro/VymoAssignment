package com.example.vymoassignment.viewmodel;

import androidx.lifecycle.ViewModel;

import com.example.vymoassignment.model.GithubRepoDetails;

public class FillGithubRepoDetailsActivityViewModel extends ViewModel {

    private final GithubRepoDetails githubRepoDetails = new GithubRepoDetails();

    public GithubRepoDetails getGithubRepoDetails() {
        return githubRepoDetails;
    }

    public ViewState getViewState() {
        if (isOwnerNameValid(githubRepoDetails.getOwnerName())) {
            if (isRepoNameValid(githubRepoDetails.getRepoName())) {
                return new ViewState.Success();
            } else {
                return new ViewState.Error("Invalid Repo name");
            }
        } else {
            return new ViewState.Error("Invalid Owner name");
        }
    }

    private boolean isOwnerNameValid(String ownerName) {
        return ownerName != null && !ownerName.isEmpty();
    }

    private boolean isRepoNameValid(String repoName) {
        return repoName != null && !repoName.isEmpty();
    }

    public interface ViewState {

        class Success implements ViewState {

        }

        class Error implements ViewState {
            private final String errorMessage;

            public Error(String errorMessage) {
                this.errorMessage = errorMessage;
            }

            public String getErrorMessage() {
                return errorMessage;
            }
        }
    }
}
