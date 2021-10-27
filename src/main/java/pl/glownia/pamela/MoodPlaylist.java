package pl.glownia.pamela;

class MoodPlaylist {
    String mood;

    MoodPlaylist(String mood) {
        this.mood = mood;
    }

    String getMood() {
        return mood;
    }

    @Override
    public String toString() {
        return mood;
    }
}
