package GameObjects;

public class CarPhysics {
    //variables
    public double x;
    public double y;
    public double z;

    //constructor
    public CarPhysics() {
    }

    public CarPhysics(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public static double calculateAngleInBetween(CarPhysics a, CarPhysics b) {
        double am = a.getMagnitude();
        double bm = b.getMagnitude();
        return Math.acos(a.dProduct(b) / (am * bm));
    }

    public static void sub(CarPhysics r, CarPhysics a, CarPhysics b) {
        r.x = a.x - b.x;
        r.y = a.y - b.y;
        r.z = a.z - b.z;
    }

    public static void cProduct(CarPhysics res, CarPhysics left, CarPhysics right) {
        double x = left.y * right.z - left.z * right.y;
        double y = right.x * left.z - right.z * left.x;
        double z = left.x * right.y - left.y * right.x;
        res.set(x, y, z);
    }

    //    methods
    public void set(CarPhysics newd) {
        this.x = newd.x;
        this.y = newd.y;
        this.z = newd.z;
    }

    public void set(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public void gyroZ(double angle) {
        double tempx = x * Math.cos(angle) - y * Math.sin(angle);
        double tempy = x * Math.sin(angle) + y * Math.cos(angle);
        double tempz = z;
        set(tempx, tempy, tempz);
    }

    public void gyroY(double angle) {
        double nz = z * Math.cos(angle) - x * Math.sin(angle);
        double nx = z * Math.sin(angle) + x * Math.cos(angle);
        double ny = y;
        set(nx, ny, nz);
    }

    public void gyroX(double angle) {
        double ny = y * Math.cos(angle) - z * Math.sin(angle);
        double nz = y * Math.sin(angle) + z * Math.cos(angle);
        double nx = x;
        set(nx, ny, nz);
    }

    public void updateCoordinates(double x, double y, double z) {

        set(this.x + x, this.y + y, this.z + z);
    }

    public double getMagnitude() {

        return Math.sqrt(x * x + y * y + z * z);
    }

    public void standardize() {
        double size = getMagnitude();
        if (size != 0) {
            x /= size;
            y /= size;
            z /= size;
        }

    }

    public void multiply(double f) {
        x *= f;
        y *= f;
        z *= f;
    }

    public void add(CarPhysics v) {
        x += v.x;
        y += v.y;
        z += v.z;
    }

    public void sub(CarPhysics v) {
        x -= v.x;
        y -= v.y;
        z -= v.z;
    }

    public double dProduct(CarPhysics v) {
        return x * v.x + y * v.y + z * v.z;
    }

    public double calculateRelative(CarPhysics v) {
        return getSign(v) * Math.acos(dProduct(v) / (getMagnitude() * v.getMagnitude()));
    }

    public int getSign(CarPhysics v) {
        return (y * v.x > x * v.y) ? -1 : 1;
    }

    public void print() {
        System.out.println(x + 400 + ", " + y + 300 + ", " + z + ", ");
    }

}
