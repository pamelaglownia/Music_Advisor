package pl.glownia.pamela;

class Featured {

    private final String name;

    Featured(String name) {
        this.name = name;
    }

    String getName() {
        return name;
    }

    @Override
    public String toString() {
        return name;
    }
}
