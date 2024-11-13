import java.util.Scanner;
import java.io.*;

public class ArrayBasedToDoList {
   private String[] tasks;
   private int count;
   private static final int INITIAL_CAPACITY = 10;

   public ArrayBasedToDoList() {
      tasks = new String[INITIAL_CAPACITY];
      count = 0;
   }
   public void addTask(String task) {
      if (count == tasks.length)
         resizeArray(2 * tasks.length);
      tasks[count++] = task;
      System.out.println("Task added: " + task);
   }

   private void resizeArray(int newCapacity) {
      String[] newTasks = new String[newCapacity];
      System.arraycopy(tasks, 0, newTasks, 0, tasks.length);
      tasks = newTasks;
   }

   public void removeTask(int position) {
      if (position < 1 || position > count) {
         System.out.println("Invalid task number: " + position);
         return;
      }
      String removedTask = tasks[position - 1];
      for (int i = position - 1; i < count - 1; i++)
         tasks[i] = tasks[i + 1];
      tasks[count - 1] = null;
      count--;
      System.out.println("Task removed: " + removedTask);
   }
   public void displayTasks() {

      if (count == 0) {
         System.out.println("To-do list is empty!");
         return;
      }
      System.out.println("To-do list:");
      for (int i = 0; i < count; i++)
         System.out.println((i + 1) + ". " + tasks[i]);
      System.out.println("Total tasks: " + count);
   }
   public void saveTasks(String filename) {
      if (count == 0) {
         System.out.println("To-do list is empty! No tasks to save.");
         return;
      }
      try {
         File file = new File(filename);
         PrintWriter output = new PrintWriter(file);
         for (int i = 0; i < count; i++)
            output.println(tasks[i]);
         output.close();
         System.out.println("Tasks saved to " + filename);
      } catch (Exception e) {
         System.out.println("Error saving tasks to file: " + e.getMessage());
      }
   }


   public void loadTasks(String filename) {
      try {
         File file = new File(filename);
         Scanner input = new Scanner(file);
         while (input.hasNextLine()) {
            String task = input.nextLine();
            addTask(task);
         }
         input.close();
         if (count != 0)

            System.out.println("Tasks loaded from " + filename);
         else
            System.out.println("No tasks in file: " + filename);
      } catch (Exception e) {
         System.out.println("Error loading tasks from file: " + e.getMessage());
      }
   }


   public static void main(String[] args) {
      ArrayBasedToDoList toDoList = new ArrayBasedToDoList();
      toDoList.loadTasks("tasks.txt");

      Scanner scanner = new Scanner(System.in);
      int choice;
      do {
         System.out.println("\nTo-Do List Menu:");
         System.out.println("1. Add task");
         System.out.println("2. Display tasks");
         System.out.println("3. Remove task");
         System.out.println("4. Exit");
         System.out.print("Choose an option: ");
         choice = scanner.nextInt();
         scanner.nextLine();
         System.out.println();
         switch (choice) {
            case 1:
               System.out.print("Enter the task description: ");
               String task = scanner.nextLine();
               toDoList.addTask(task);
               break;
            case 2:
               toDoList.displayTasks();
               break;
            case 3:
               System.out.print("Enter the task number to remove: ");
               int taskNumber = scanner.nextInt();
               toDoList.removeTask(taskNumber);
               break;
            case 4:
               toDoList.saveTasks("tasks.txt");
               System.out.println("Exiting...");
               break;
            default:
               System.out.println("Invalid option. Please try again.");
         }

      } while (choice != 4);
      scanner.close();
   }
}
