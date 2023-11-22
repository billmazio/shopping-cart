package seminars.project.security;



import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;



public class BCryptPasswordHash {

    public static void main(String[] args) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String rawPassword = "Basil";
        String hashedPassword = passwordEncoder.encode(rawPassword);

        System.out.println("Encoded Password: " + hashedPassword);
    }
}




