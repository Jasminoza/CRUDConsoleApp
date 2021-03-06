package view;

import controller.SkillController;
import model.Skill;

import java.util.Scanner;

public class SkillView {
    private final Scanner scanner = new Scanner(System.in);

    private final SkillController skillController = new SkillController();

    public void createSkill() {
        System.out.println("Enter skill name: ");
        String name = scanner.nextLine();
        Skill skill = skillController.createSkill(name);
        System.out.println("Created skill: " + skill);
        System.out.println("id: " + skill.getId() + ", name: " + skill.getName());
    }

    public void showAllSkills() {

        if (skillController.getAllSkills() != null && skillController.getAllSkills().size() != 0) {
            System.out.println("Skills:\n===============================");
            skillController.getAllSkills().
                    forEach(a -> System.out.println(" id: " + a.getId() + ", name: " + a.getName() + ";"));
            System.out.println("===============================");
        } else {
            System.out.println("Skill list is empty.");
        }

    }

    public void deleteSkill() {
        boolean idIsCorrect = false;
        Long id;
        if (skillController.getAllSkills() != null && skillController.getAllSkills().size() != 0) {
            System.out.println("Enter id number to delete skill from the list: ");

            while (!idIsCorrect) {
                try {
                    id = Long.parseLong(scanner.nextLine());
                    final Long finalId = id;
                    if (skillController.getAllSkills().stream().anyMatch(s -> s.getId().equals(finalId))) {
                        idIsCorrect = true;
                        skillController.deleteSkill(id);
                    } else {
                        System.out.println("There is no skill with such id. Please, try again.");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Please, enter correct id.");
                }
            }
        } else {
            System.out.println("Skill list is empty.");
        }
    }

    public void updateSkill() {
        boolean idIsCorrect = false;
        Long id;

        if (skillController.getAllSkills() != null && skillController.getAllSkills().size() != 0) {

            showAllSkills();
            System.out.println("Please, enter id number of skill you want to update: ");

            while (!idIsCorrect) {
                try {
                    id = Long.parseLong(scanner.nextLine());
                    final Long finalId = id;
                    if (skillController.getAllSkills().stream().anyMatch(s -> s.getId().equals(finalId))) {
                        idIsCorrect = true;
                        System.out.println("Please, enter new name: ");
                        String name = scanner.nextLine();
                        skillController.updateSkill(id, name);
                    } else {
                        System.out.println("There is no skill with such id. Please, try again.");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Please, enter correct id.");
                }
            }
        } else {
            System.out.println("Skill list is empty.");
        }
    }

    public void getById() {
        boolean idIsCorrect = false;
        Long id;

        if (skillController.getAllSkills().size() != 0 && skillController.getAllSkills() != null) {

            showAllSkills();
            System.out.println("Please, enter number of skill you want to see: ");

            while (!idIsCorrect) {
                try {
                    id = Long.parseLong(scanner.nextLine());
                    final Long finalId = id;
                    if (skillController.getAllSkills().stream().anyMatch(s -> s.getId().equals(finalId))) {
                        idIsCorrect = true;
                        System.out.println("id: " + skillController.getById(id).getId() + ", name: " + skillController.getById(id).getName() + ".");
                    } else {
                        System.out.println("There is no skill with such id. Please, try again.");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Please, enter correct id.");
                }
            }
        } else {
            System.out.println("Skill list is empty.");
        }
    }

}
