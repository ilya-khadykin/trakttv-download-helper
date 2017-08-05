package model;

public class TvShow {
    private String currentSeasonNumber;
    private String nextEpisodeNumber;
    private String nextEpisodeName;
    private String tvShowName;

    public TvShow(String currentSeasonNumber, String nextEpisodeNumber, String nextEpisodeName, String tvShowName) {
        this.currentSeasonNumber = currentSeasonNumber;
        this.nextEpisodeNumber = nextEpisodeNumber;
        this.nextEpisodeName = nextEpisodeName;
        this.tvShowName = tvShowName;
    }

    public String getCurrentSeasonNumber() {
        return currentSeasonNumber;
    }

    public void setCurrentSeasonNumber(String currentSeasonNumber) {
        this.currentSeasonNumber = currentSeasonNumber;
    }

    public String getNextEpisodeNumber() {
        return nextEpisodeNumber;
    }

    public void setNextEpisodeNumber(String nextEpisodeNumber) {
        this.nextEpisodeNumber = nextEpisodeNumber;
    }

    public String getNextEpisodeName() {
        return nextEpisodeName;
    }

    public void setNextEpisodeName(String nextEpisodeName) {
        this.nextEpisodeName = nextEpisodeName;
    }

    public String getTvShowName() {
        return tvShowName;
    }

    public void setTvShowName(String tvShowName) {
        this.tvShowName = tvShowName;
    }
}
