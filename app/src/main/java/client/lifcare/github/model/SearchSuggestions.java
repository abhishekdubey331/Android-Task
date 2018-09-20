package client.lifcare.github.model;

import android.os.Parcel;

import com.arlib.floatingsearchview.suggestions.model.SearchSuggestion;

public class SearchSuggestions implements SearchSuggestion {
    public String getSuggestion() {
        return suggestion;
    }

    public void setSuggestion(String suggestion) {
        this.suggestion = suggestion;
    }

    private String suggestion;
    private boolean mIsHistory = false;

    public SearchSuggestions(String suggestion) {
        this.suggestion = suggestion.toLowerCase();
    }

    public SearchSuggestions(Parcel source) {
        this.suggestion = source.readString();
        this.mIsHistory = source.readInt() != 0;
    }

    public void setIsHistory(boolean isHistory) {
        this.mIsHistory = isHistory;
    }

    public boolean getIsHistory() {
        return this.mIsHistory;
    }

    @Override
    public String getBody() {
        return suggestion;
    }

    public static final Creator<SearchSuggestions> CREATOR = new Creator<SearchSuggestions>() {
        @Override
        public SearchSuggestions createFromParcel(Parcel in) {
            return new SearchSuggestions(in);
        }

        @Override
        public SearchSuggestions[] newArray(int size) {
            return new SearchSuggestions[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(suggestion);
        dest.writeInt(mIsHistory ? 1 : 0);
    }

}
