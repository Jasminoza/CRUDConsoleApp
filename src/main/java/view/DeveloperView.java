package view;

import controller.DeveloperController;
import controller.SkillController;
import controller.SpecialtyController;
import model.Skill;
import model.Specialty;
import model.Status;

import java.util.*;

public class DeveloperView {

    private final DeveloperController developerController = new DeveloperController();
    private final SkillController skillController = new SkillController();
    private final SpecialtyController specialtyController = new SpecialtyController();
    private final Scanner scanner = new Scanner(System.in);

    public void createDeveloper() {
        System.out.println("Enter developer's first name: ");
        String firstname = scanner.nextLine();
        System.out.println("Enter developer's last name: ");
        String lastname = scanner.nextLine();
        List<Skill> skills = new ArrayList<>();
        if (skillController.getAllSkills() != null && skillController.getAllSkills().size() == 0) {
            System.out.println("Please, add some skills to skills list first, its empty.");
        } else {
            Map<Long, Skill> chosenSkills = new HashMap<>();
            showAllSkills();
            skills = addSkillsToList(chosenSkills);
        }
        System.out.println("Enter id of specialty you want to add: ");
        Specialty specialty = chooseSpecialty();
        Status status = Status.ACTIVE;
        developerController.createDeveloper(firstname, lastname, skills, specialty, status);
    }

    public void showAllSkills() {

        if (skillController.getAllSkills().size() != 0) {
            System.out.println("Skills:\n===============================");
            skillController.getAllSkills().
                    forEach(skill -> System.out.println(" id: " + skill.getId() + ", name: " + skill.getName() + ";"));
            System.out.println("===============================");
        } else {
            System.out.println("Skill list is empty.");
        }

    }

    private List<Skill> addSkillsToList( Map<Long, Skill> chosenSkills) {
        System.out.println("Enter id of skill you want to add: ");
        return addSkills(chosenSkills);
    }

    private List<Skill> addSkills(Map<Long, Skill> chosenSkills) {
        try {
            Long id = Long.parseLong(scanner.nextLine());
            if (skillController.getById(id) == null) {
                System.out.println("There is no skill with such id. Please, try again.");
            } else {
                addSkillsToMapIfNotExist(chosenSkills, id);
                System.out.println("Do you want to add another skill? (y/n)");
                String answer = scanner.nextLine();
                if (answer.equalsIgnoreCase("no") || answer.equalsIgnoreCase("n")) {
                    return chosenSkills.values().stream().toList();
                }
            }
        } catch (NumberFormatException e) {
            System.out.println("Input incorrect");
        }
        return addSkillsToList(chosenSkills);
    }

    private void addSkillsToMapIfNotExist(Map<Long, Skill> chosenSkills, Long id) {
        if (!chosenSkills.containsKey(id)) {
            chosenSkills.put(id, skillController.getById(id));
        } else {
            System.out.println("Chosen skill is already selected.");
        }
    }

    private Specialty chooseSpecialty() {
        boolean idIsCorrect = false;
        Long id;

        showAllSpecialties();
        System.out.println("Please, enter id number of specialty you want to choose: ");
        while (!idIsCorrect) {
            try {
                id = Long.parseLong(scanner.nextLine());
                final Long finalId = id;
                if (specialtyController.getAllSpecialties().stream().anyMatch(s -> s.getId().equals(finalId))) {
                    idIsCorrect = true;
                    return specialtyController.getById(id);
                } else {
                    System.out.println("There is no specialty with such id. Please, try again.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Please, enter correct id.");
            }
        }
        return null;
    }

    public void showAllSpecialties() {
        if (specialtyController.getAllSpecialties().size() != 0) {
            System.out.println("Specialties:\n===============================");
            specialtyController.getAllSpecialties()
                    .forEach(spec -> System.out.println("id: " + spec.getId() + ", name: " + spec.getName()));
            System.out.println("===============================");
        } else {
            System.out.println("Specialties list is empty.");
        }
    }

    public void showAllDevelopers() {

        if (Objects.nonNull(developerController.getAllDevelopers())) {
            System.out.println("Developers:\n=============================================================================================");
            developerController.getAllDevelopers()
                    .forEach(dev -> System.out.println("id: " + dev.getId() + ", first name: " + dev.getFirstName()
                            + ", last name: " + dev.getLastName() + ", skills: " + dev.showSkills(dev.getSkills())
                            + "specialty: " + dev.getSpecialty().getName() + ", status: " + dev.getStatus().toString()));
            System.out.println("=============================================================================================");
        } else {
            System.out.println("Developers list is empty.");
        }
    }

    public void deleteDeveloper() {
        boolean idIsCorrect = false;
        Long id;
        if (Objects.nonNull(developerController.getAllDevelopers())) {
            System.out.println("Enter id number to delete developer from the list: ");
            while (!idIsCorrect) {
                try {
                    id = Long.parseLong(scanner.nextLine());
                    final Long finalId = id;
                    if (developerController.getAllDevelopers().stream().anyMatch(s -> s.getId().equals(finalId))) {
                        idIsCorrect = true;
                        developerController.deleteDeveloper(id);
                    } else {
                        System.out.println("There is no developer with such id. Please, try again.");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Please, enter correct id.");
                }
            }
        } else {
            System.out.println("Developers list is empty.");
        }
    }

    public void updateDeveloper() {

    }

    public void getById() {
        boolean idIsCorrect = false;
        Long id;

        if (Objects.nonNull(developerController.getAllDevelopers())) {
            showAllDevelopers();
            System.out.println("Please, enter the ID of the developer you want to view: ");

            while (!idIsCorrect) {
                try {
                    id = Long.parseLong(scanner.nextLine());
                    final Long finalId = id;
                    if (developerController.getAllDevelopers().stream().anyMatch(s -> s.getId().equals(finalId))) {
                        idIsCorrect = true;
                        System.out.println("id: " + developerController.getById(id).getId() + ", first name: " + developerController.getById(id).getFirstName()
                                + ", last name: " + developerController.getById(id).getLastName() + ", skills: " + developerController.getById(id).showSkills(developerController.getById(id).getSkills())
                                + "specialty: " + developerController.getById(id).getSpecialty().getName() + ", status: " + developerController.getById(id).getStatus().toString());
                    } else {
                        System.out.println("There is no developer with such id. Please, try again.");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Please, enter correct id.");
                }
            }
        } else {
            System.out.println("Developers list is empty.");
        }
    }
}


