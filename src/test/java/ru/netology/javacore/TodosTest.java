package ru.netology.javacore;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class TodosTest {
    Todos todos;

    @BeforeEach
    void initializeData() {
        todos = new Todos();
    }

    @Test
    @DisplayName("Повторяем пример из задания, лоджен быть результат \"Вторая Первый\"")
    public void someTransactionMustHaveExpectedResult() {
        todos.addTask("Первая");
        todos.addTask("Вторая");
        todos.removeTask("Первая");
        todos.addTask("Третья");
        todos.restoreTask();
        todos.restoreTask();
        Assertions.assertEquals("Вторая Первая", todos.getAllTasks());
    }

    @Test
    @DisplayName("It is impossible to add tasks above the maximum")
    public void itIsImpossibleToAddTasksAboveTheMaximum() {
        // TODO Заданием установлен размер списка в 7, при изменении константы тест перестанет работать
        for (Integer i = 0; i < 7; i++) {
            todos.addTask(i.toString());
        }
        Assertions.assertEquals("0 1 2 3 4 5 6", todos.getAllTasks());
        todos.addTask("8");
        Assertions.assertEquals("0 1 2 3 4 5 6", todos.getAllTasks());
    }

    @Test
    @DisplayName("Метод возвращает все задачи через пробел в отсортированном лексикографическом порядке")
    public void theMethodReturnsAllTasksSeparatedBySpaceInSortedOrder() {
        todos.addTask("Пробежка");
        todos.addTask("Акробатика");
        todos.addTask("Учёба");
        Assertions.assertEquals("Акробатика Пробежка Учёба", todos.getAllTasks());
    }
}