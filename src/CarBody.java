class CarBody {
    private String color;
    private Material material;

    public CarBody(String clr, Material mat) {
        color = clr;
        material = mat;
    }

    public String getColor() {
        return color;
    }

    public Material getMaterial() {
        return material;
    }
}