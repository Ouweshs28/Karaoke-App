public class TesterConsole {
    public static void main(String[] args) {
        FileManagement songfile = new FileManagement();
        songfile.getFile("sample_song_data");
        StopWatch create =new StopWatch();
        StopWatch search =new StopWatch();
        create.start();
        HashST<String, Song> song = new HashST<String, Song>();
        while (songfile.scan.hasNextLine()) {
            String line = songfile.scan.nextLine();
            String[] details = line.split("\t");
            String title = details[0];
            String artist = details[1];
            double time = Double.parseDouble(details[2]);
            String videofile = details[3];
            Song p = new Song(title, artist, time, videofile);
            song.put(title, p); 
        }
        System.out.println("Data structure created took " +create.stop() +" seconds");
        search.start();
        String seatchtitle="My Friend, the Dictionary";
        System.out.println("Song found: "+song.get(seatchtitle));
        if(song.get(seatchtitle)!=null){
            Song searchsong=song.get(seatchtitle);

            System.out.println(searchsong.getArtist());
        }
        System.out.println("search complete " +search.stop() +" seconds");
        
    }

}