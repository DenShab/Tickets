package com.is.examination_tickets;

/*Для использования, к проекту необходимо подключить библиотеку, которую можно взять отсюда.
 *  Далее импортируются необходимые классы аннотаций
 */
import org.junit.Test;
/*  Импорт статических методов класса Assert */
import static org.junit.Assert.*;
/* Подключаем библиотеки для работы с ArrayList и Arrays */
import java.util.ArrayList;
import java.util.Arrays;

public class TicketGenerator1Test {

//проверим соответсвует ли колличество составленных билетов заданному
	@Test
	public void testNumberOfTicketsTrue() {
		// Опишем вхолдные данные
		// questions - список вопросов
		ArrayList<String> questions = new ArrayList<String>(
				Arrays.asList("question_1", "question_2", "question_3", "question_4", "question_5"));
		// numberOfTickets - колличество билетов, ожидаемое значение
		int numberOfTickets = 10;
		// questionsInTickets - колличество вопросов в билете
		int questionsInTickets = 2;
		// realNumberOfTickets - колличество билетов, действительное значение
		// пока что инициализируем нулем
		int realNumberOfTickets = 0;
		// ticket - список билетов
		// TicketGenerator.generator - тестируемый метод
		ArrayList<ArrayList<String>> ticket = TicketGenerator.generator(questions, numberOfTickets, questionsInTickets);
		// Запишем в realNumberOfTickets действительное колличество билетов
		realNumberOfTickets = ticket.size();
		// assertEquals-Утверждение эквивалентности
		// Первый параметр (expected) - ожидаемое значение
		// Второй параметр (actual) - действительное значение контролируемого аргумента
		assertEquals(numberOfTickets, realNumberOfTickets);
		// тест считается успешным если значения эквивалентны
	}

	// проверим случай если колличество билетов задано больше чем можно составить с
	// данным набором вопросов
	@Test
	public void testNumberOfTicketsNull() {
		ArrayList<String> questions = new ArrayList<String>(
				Arrays.asList("question_1", "question_2", "question_3", "question_4", "question_5"));
		// Из пяти вопросов можно составить 10 различных билетов состоящих из 2-х
		// вопросов
		// Зададим колличество билетов больше возможного
		int numberOfTickets = 11;
		// Колличество вопросов в билете - 2
		int questionsInTickets = 2;
		// Генерируем билеты
		ArrayList<ArrayList<String>> ticket = TicketGenerator.generator(questions, numberOfTickets, questionsInTickets);
		// assertTrue - Булевы утверждения
		// Поскольку билетов требуется больше возможного , билеты не сгенерируются
		assertTrue(ticket == null);
		// Тест считается успешным если выполняется логическое выражение

	}
}
