public class Song {
    private String title;
    private String artist;
    private int time;
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

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Song(){
        this.title = "";
        this.artist = "";
        this.time = 0;
        this.videofile = "";

    }

    public Song(String title, String artist) {
        this.title = title;
        this.artist = artist;
    }

    public Song(String title, String artist, int time) {
        this.title = title;
        this.artist = artist;
        this.time = time;
    }

    public Song(String title, String artist, int time, String videofile) {
        this.title = title;
        this.artist = artist;
        this.time = time;
        this.videofile = videofile;
    }



    public int compareTo(Song p)
    {
        int d = getTitle().compareTo(p.getTitle());
        if (d == 0)
            d = getTitle().compareTo(p.getTitle());
        return d;
    }

    public static int compareThem(Song a, Song b) {
        return a.title.compareTo(b.title);
    }

}