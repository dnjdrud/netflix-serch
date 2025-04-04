package main.com.dnjdrud.main;

import main.com.dnjdrud.model.User;
import main.com.dnjdrud.service.AuthService;
import main.com.dnjdrud.service.PreferenceService;
import main.com.dnjdrud.feature.SearchUI;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        AuthService authService = new AuthService();

        System.out.println("📥 로그인해주세요");
        System.out.print("아이디: ");
        String username = scanner.nextLine();

        System.out.print("비밀번호: ");
        String password = scanner.nextLine();

        main.com.dnjdrud.model.User user = authService.login(username, password);

        if (user != null) {
            System.out.println("\n✅ 로그인 성공!");
            System.out.println(user.getUsername() + "님 환영합니다!\n");

            PreferenceService preferenceService = new PreferenceService();
            preferenceService.printUserPreferences(user);

            SearchUI searchUI = new SearchUI();

            while (true) {
                System.out.print("\n콘텐츠를 검색하시겠습니까? (YES/NO): ");
                String answer = scanner.nextLine().trim();

                if (answer.equalsIgnoreCase("YES")) {
                    int choice = searchUI.showMenuAndSelect();
                    searchUI.handleChoice(choice, user);
                } else {
                    System.out.println("이용해주셔서 감사합니다! 👋");
                    break;
                }
            }

        } else {
            System.out.println("❌ 로그인 실패! 아이디나 비밀번호를 확인해주세요.");
        }
    }
}
