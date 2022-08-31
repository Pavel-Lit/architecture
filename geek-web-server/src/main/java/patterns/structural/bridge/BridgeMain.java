package patterns.structural.bridge;

public class BridgeMain {

    public static void main(String[] args) {

        AquaTheme aquaTheme = new AquaTheme();
        LightTheme lightTheme = new LightTheme();
    DarkTheme darkTheme = new DarkTheme();

    About aboutDark = new About(darkTheme);
    About aboutLight = new About(lightTheme);
    About aboutAqua = new About(aquaTheme);
    Careers careersDark = new Careers(darkTheme);
    Careers careersLight = new Careers(lightTheme);
    Careers careersAqua = new Careers(aquaTheme);

        System.out.println(aboutAqua.getContent());
        System.out.println(aboutDark.getContent());
        System.out.println(aboutLight.getContent());
        System.out.println(careersAqua.getContent());
        System.out.println(careersDark.getContent());
        System.out.println(careersLight.getContent());
    }

}
