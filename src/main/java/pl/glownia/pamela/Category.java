package pl.glownia.pamela;

class Category {

    String category;

    Category(String category) {
        this.category = category;
    }

    String getCategory() {
        return category;
    }

    @Override
    public String toString() {
        return category;
    }
}
