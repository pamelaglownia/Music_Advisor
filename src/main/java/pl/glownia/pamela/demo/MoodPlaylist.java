package pl.glownia.pamela.demo;

class MoodPlaylist {
    String mood;

    MoodPlaylist(String mood) {
        this.mood = mood;
    }

    @Override
    public String toString() {
        return mood;
    }
}
