public class Song {
    private String title;
    private String artist;
    private double time;
    private String videofile;

    public String getTitle() {
        return title;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getVideofile() {
        return videofile;
    }

    public void setVideofile(String videofile) {
        this.videofile = videofile;
    }

    public double getTime() {
        return time;
    }

    public void setTime(double time) {
        this.time = time;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Song(String title, String artist, double time, String videofile) {
        this.title = title;
        this.artist = artist;
        this.time = time;
        this.videofile = videofile;
    }

}