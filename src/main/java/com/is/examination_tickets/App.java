package com.is.examination_tickets;// Пакет (package) представляют собой набор классов, объединённых по смыслу.

import java.awt.EventQueue; //импортируем класс для управления компонентами swing
import java.sql.SQLException; // И мпортируем класс для обработки SQL исключений 

public class App // В этом классе реализуем главную функцию
{
    public static void main( String[] args ) 
    		/*Ключевое слово main() - основной метод. Это - строка, с которой начинается выполнение программы.
    		 * public - это спецификатор доступа, main() достумен везде
    		 * void - ключевое слово обозначает что метод ничего не возвращает */
    		throws ClassNotFoundException, SQLException
    		/*throws - предупредит что данный метод может бросать такое-то исключение и его надо обработать при вызове метода*/
    {
    	EventQueue.invokeLater
    	/*отправляет событие (Runnable) в конце списка событий Swings и обрабатывается после обработки всех предыдущих графических событий.
    	  */
    	(new Runnable() { //создаем и запускаем поток
    		//Runnable представляет собой функциональный 
			public void run() {//Этот метод будет выполняться в побочном потоке 
				try {
					
					StartWin frame2 = null;
					frame2 = new StartWin();
					frame2.frame.setVisible(true); 
				} catch (Exception e) { /* Перехватываем исключения */
					e.printStackTrace(); /* Обрабатыаем исключения */
				}
			}
		});
    }
}
