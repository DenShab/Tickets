/*Пакет (package) — это некий контейнер, который используется для того, чтобы изолировать имена классов. 
com.is.examination_tickets - название пакета */
package com.is.examination_tickets;
/*	java. - Все стандартные классы, поставляемые с системой Java, хранятся в пакете java.
java.awt  - подключение пакета awt, содержащего базовые графические функции:GridLayout, BorderLayout и т.д.
"*"-включение всех элементов библиотеки awt */	
import java.awt.*;
import java.awt.event.*;
/* java.sql - платформенно-независимый стандарт взаимодействия Java-приложений с различными СУБД
 java.sql.SQLException-  наследуется от Exception и является, таким образом, checked exception. Кроме стандартных данных, которые передаются в Exception, SQLException всегда содержит дополнительные данные о ошибке. */
import java.sql.SQLException;
import java.util.ArrayList;
/*	Импортируем: 
javax. * — стандартные расширения. (Стандартные расширения (standard extensions) — это пакеты или наборы пакетов Java)
Swing - Библиотека графического интерфейса (конкретный пакет), В отличие от java.awt, javax.swing имеет более гибкую систему управления и более широкий набор функций
"*"-включение всех элементов библиотеки Swing (jbutton,jpanel,jlabel и т.д.)
"."(точка) используется для выделения элементов из ссылки на объект. */
import javax.swing.*;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;
// Импортируем свой класс для работы с бд
import bd.conn;
import net.miginfocom.swing.MigLayout;
//import net.miginfocom.swing.MigLayout;
/*	(Public) - поля и методы класса Index доступны для всех других объектов и классов. 
зарезервированное слово class - говорит транслятору, что мы собираемся описать новый класс MainWin.
Класс наследует функции элемента JFrame, который определён в стандартной библиотеке awt.
 JFrame - само окно. JFrame содержит в себе всё необходимое для создания и функционирования окна программы - мы 
 используем его методы в своей программе. { }-описание класса располагается между фигурными скобками. 
*/
public class MainWin extends JFrame {


	private static final long serialVersionUID = 1L;
	protected static final Component D = null;
	// Панель на которой располагаются поля и кнопки. 
	// Панель доступна только для данного класса (private)
	private JPanel contentPane;
	/* conn – класс осуществляющий создание и взаимодействие с базой данных .
	db- обьект класса conn
	new – оператор создает экземпляр указанного класса и возвращает ссылку на него
	conn()-конструктор класса conn. */
	private conn db=new conn();
	/*String-класс строк представляет собой последовательность символов.
	Null – означает что мы не создаем экземпляр указанного класса. */
	private String disciplin = null;
	/* JComboBox<Strin>- это компонент Java Swing, который представляет собой выпадающий список строк. 
	В comboBox содержится список дисциплин. В comboBox_1 содержится список групп.  */
	private JComboBox<String> comboBox = new JComboBox<String>();
	private JComboBox<String> comboBox_1 = new JComboBox<String>();
	/* JList<String>-Компонент в Java Swing используется для отображения данных в виде списка. 
	list содержит список вопросов по некоторой дисциплине.
	list_1 содержит список билетов по некоторой дисциплине. */
	private JList<String> list = new JList<String>();
	private JList<String> list_1= new JList<String>();
	/* JEditorPane -Компонент в Java Swing используется для ввода и редактирования текста, JEditorPane имеет поддержку стилей и многострочного ввода. */
	private JEditorPane editorPane = new JEditorPane();
	
	/* JTextField текстовое поле. */
	private JTextField textField;
	private JTextField textField_1;
	/* определение метода readDisciplin который принимет (conn db )
	, и возвращает значение типа ArrayList<String>.
	ArrayList<String> автоматически расширяемый массив.
	Throws - ключевое слово, которое используется для того, чтобы бросить исключение во время выполнения.  */
	static ArrayList<String> readDisciplin(conn db ) throws ClassNotFoundException, SQLException 
	    {
		/* ReadDisciplin – метод класса conn который возвращает массив дисциплин*/	
		ArrayList<String[]> list =db.ReadDisciplin();
		/* res  переменная типа ArrayList<String> будет хранить результат работы метода */	
		ArrayList<String>res=new ArrayList<String>();
	    	 for (int i=0; i < list.size(); i++)
	         {
	    		 /* res.add(obj) добавление объекта в список.
	    		 list.get(i)-получение i-го объекта списка list. */
	    		 res.add(list.get(i)[1]);
	         }
	    	 /* return res – возвращает res в ту часть программы где был вызван метод */
	    	return res;
	    	}
	/* метод serchID для поиска id записи «d» в таблице «table», возвращает целочисленное значение. */
	int serchID(conn db,String tabel,String d) throws ClassNotFoundException, SQLException 
	{
		// переменная типа int в которую запишем найденый id
		int id = 0;
		ArrayList<String[]> list;
		/* Условная конструкция для определения таблици из которой делать выборку */
		if("Disciplin".equals(tabel))
			list=db.ReadDisciplin(); 
			else if("Group".equals(tabel))
				list=db.ReadGroup(); 
				else if("Question".equals(tabel))
					list=db.ReadQuestion(); 
					else return id =-1;
		
		for (int i=0; i < list.size(); i++)
        {
			/*В таблице вопросов столбец по записям котрого будем искать находится на другом месте 
			 * поэтому с помощю условной конструкции определяем в какой таблице ищем */
			if(!"Question".equals(tabel))
			{
				/*Ищем в полученой выборке совпадение заданой строкой*/
				if(d.equals(list.get(i)[1]))
					/* Id айденой записи парсим(преобразуем) в int и сохраняем в id*/
					id=Integer.parseInt(list.get(i)[0]);
			}
			else 
			{
				/* тут аналогично 
				 * изменение лишь в том что сравниваес строку с записями из 2 столбца таблици*/
				if(d.equals(list.get(i)[2]))
					id=Integer.parseInt(list.get(i)[0]);
			}
        }
		/*возвращаем найденный Id*/
		return id;
	}
	/* readGroup -  чтение записей из таблици групп и представление их в удобном виде для вывода и работы в графическом интерфейсе */
	static ArrayList<String> readGroup(conn db,String disciplin) throws ClassNotFoundException, SQLException 
	    {
		/* в массив list запишем выборку из таблици */
	    	ArrayList<String[]> list =db.ReadGroup();
	    	/* в массив res будем записывать нужные строки */
	    	ArrayList<String>res=new ArrayList<String>();
	    	 for (int i=0; i < list.size(); i++)
	         {
	    		 /* добавляем в res только названия групп */
	    		 res.add(list.get(i)[1]);
	         }
	    	return res;
	    }
	 /*Аналогичным образом создаем методы для чтения вопросов и билетов */
	 static ArrayList<String> readQuestionWHERE(conn db,int Disciplin) throws ClassNotFoundException, SQLException 
	    {
	    	ArrayList<String[]> list =db.ReadQuestionWHERE(Disciplin);
	    	ArrayList<String>res=new ArrayList<String>();
	    	 for (int i=0; i < list.size(); i++)
	         {
	    		 /*в res добавляем строки вида "Вопрос (номер вопроса) - (текст вопроса)"*/
	    		 res.add("Вопрос "+(i+1)+" - "+list.get(i)[2]);
	         }
	    	return res;
	    }
	 /* readQuestionTextWHERE метод выводит в массив только тексты вопросов */
	 static ArrayList<String> readQuestionTextWHERE(conn db,int Disciplin) throws ClassNotFoundException, SQLException 
	    {
	    	ArrayList<String[]> list =db.ReadQuestionWHERE(Disciplin);
	    	ArrayList<String>res=new ArrayList<String>();
	    	 for (int i=0; i < list.size(); i++)
	         {
	    		 res.add(list.get(i)[2]);
	         }
	    	return res;
	    }
	 /* readTicketWHERE метод вывода билетов по дисциплинам и группам */
	 static ArrayList<String> readTicketWHERE(conn db,int Disciplin, int Group) throws ClassNotFoundException, SQLException 
	    {
	    	ArrayList<String[]> list =db.ReadTicketWHERE(Disciplin,Group);
	    	ArrayList<String>res=new ArrayList<String>();
	    	 for (int i=0; i < list.size(); i++)
	         {
	    		 res.add(list.get(i)[0]+" "+list.get(i)[1]+" "+list.get(i)[2]+" "+list.get(i)[3]);
	         }
	    	return res;
	    }
	 /* readTicketWHERE метод вывода билетов по дисциплинам и группам с текстоми вопросов */
	 static ArrayList<ArrayList<String>> readTicketWHERE2(conn db,int Disciplin, int Group) throws ClassNotFoundException, SQLException 
	    {
		
	    	ArrayList<String[]> list =db.ReadTicketWHERE(Disciplin,Group);
	    	ArrayList<String>res;
	    	ArrayList<ArrayList<String>> tmp=new ArrayList<ArrayList<String>>();
	    	
	    	 for (int i=0; i < list.size(); i++)
	         {
	    		 String d =db.ReadQuestionWHEREidQuestion(Integer.parseInt(list.get(i)[3]))[2];
	    		res=new ArrayList<String>();
	    		res.add(d);
	    		res.add(list.get(i)[4]);
	    		 tmp.add(res);
	    		 }
	    	return tmp;
	    }
	 /* Метод question обновляет вывод вопросов на графический интерфейс*/
	 void question() throws ClassNotFoundException, SQLException 
	 {/*Находим и записываем в idDisciplin выбранную в comboBox дисциплину */
		 int idDisciplin=serchID(db,"Disciplin",(String)(comboBox.getSelectedItem()));
		/*Делаем выборку вопросов по этой дисциплине из бд*/	
		 ArrayList<String> listTicket =readQuestionWHERE(db,idDisciplin);
		 /* DefaultListModel – класс для определения модели и содержимого JList. * /
		 /*listModelQuestion.addElement() – добавление элемента в список. */	
		 DefaultListModel<String> listModelTicket = new DefaultListModel<String>();
			for (int i = 0; i < listTicket.size(); i++) {
				/*Добавляем текст вопроса в listModelTicket*/
				listModelTicket.addElement(listTicket.get(i));
			}
			/* Обновляем модель у list*/
			list.setModel(listModelTicket);
	 }
	 /* метод ticket () производит обновление списка билетов*/
	 void ticket() throws ClassNotFoundException, SQLException 
	 {
		 /*Находим и записываем в idDisciplin выбранную в comboBox дисциплину */
	 int idDisciplin=serchID(db,"Disciplin",(String)(comboBox.getSelectedItem()));
	/*Аналогичным образом  записываем idGroup*/	
	 int idGroup=serchID(db,"Group",(String)(comboBox_1.getSelectedItem()));
	/*далее метод аналогичен методу question*/	
	 ArrayList<ArrayList<String>> ticket=readTicketWHERE2(db,idDisciplin,  idGroup);
		DefaultListModel<String> listModelTicket2 = new DefaultListModel<String>();
		String bilet="";
		list_1.clearSelection();
		int j=0;
		for(int i=0;i<ticket.size();i++) 
		{
		/* Для более удобного вида будем выводить сначала номер билета а после него вопросы которые в него входят
		 * Для этого воспользуемся условным оператором 
		 * Если номер билета такой же как у выведеного до этого вопроса то выводим только вопрос */
			if(bilet.equals(ticket.get(i).get(1))) {
				listModelTicket2.addElement("Вопрос "+(j+1)+" - "+ ticket.get(i).get(0));
				j++;
			}
			/* Если номер изменился то выводим номер билета только вопрос */
			else {
				bilet=ticket.get(i).get(1);
				j=0;
				listModelTicket2.addElement("БИЛЕТ "+(Integer.parseInt(bilet)+1));
				listModelTicket2.addElement("Вопрос "+(j+1)+" - "+ ticket.get(i).get(0));
				j++;
			}
				
			
		}
		list_1.setModel(listModelTicket2);
	 }
	 //метод SaveTicketToDb сохраняет билеты в БД
	 protected void SaveTicketToDb(conn db2, ArrayList<ArrayList<String>> ticket) throws ClassNotFoundException, SQLException
		{
		 //вычисляем ID группы и дициплины 
			 int idDisciplin=serchID(db,"Disciplin",(String)(comboBox.getSelectedItem()));
				int idGroup=serchID(db,"Group",(String)(comboBox_1.getSelectedItem()));
				//Удаляем старые билеты из БД
				db2.DelTicket(idDisciplin,idGroup);
			for(int i=0;i<ticket.size();i++) 
			{
				for(int j=0;j<ticket.get(i).size();j++) 
				{
					//вычисляем ID вопроса
					int idQuestion=serchID(db,"Question",ticket.get(i).get(j));
					// Делаем вставку записи в БД
					db2.AddTicket(null,idDisciplin,idGroup,idQuestion,i);
				}
			}
		}
/* Метод TicketOutput обновляет вывод билетов нв графическом интерфейсе из входного массива*/
		protected void TicketOutput(ArrayList<ArrayList<String>> ticket) {
			DefaultListModel<String> listModelTicket = new DefaultListModel<String>();
			list_1.clearSelection();
			for(int i=0;i<ticket.size();i++) 
			{
				listModelTicket.addElement("БИЛЕТ "+(i+1));
				for(int j=0;j<ticket.get(i).size();j++) 
				{
					listModelTicket.addElement("Вопрос "+(j+1)+" - "+ ticket.get(i).get(j));
				}
			}
			list_1.setModel(listModelTicket);
		}
		
		private void TicketOutputException(String s) {
			list_1.clearSelection();
			DefaultListModel<String> listModelTicket2 = new DefaultListModel<String>();
			
			listModelTicket2.addElement(s);
			
			list_1.setModel(listModelTicket2);
		}
		/* MainWin () – конструктор класса MainWin.
		Public- означает что фрейм MainWin () виден и доступен любому классу. */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public MainWin() throws ClassNotFoundException, SQLException
	{
		setResizable(false);// устанавливаем, что пользователь н е может изменить размеры окна
		db.Conn();// устанавливает соединение с базой данных
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);// по закрытию формы - закрывать приложение
		setBounds(100, 100, 806, 474);/* Метод пакета определяет размер кадра так, чтобы все его содержимое находилось на уровне или превышало их предпочтительные размеры.*/
		contentPane = new JPanel();/*  определение основной панели на которой будут располагаться все элементы окна. */
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5)); // устанавливает контур
		setContentPane(contentPane);// Позволяет заменить панель содержимого окна.
		
		JPanel panel = new JPanel();
		JPanel panel_4 = new JPanel();//Панель содержит панель с прокруткой на которой расположен лист билетов
		
		JLabel lblNewLabel = new JLabel("Дисциплины");//определение JLabel «дисциплины»
		JLabel label = new JLabel("Группы");//определение JLabel «Группы»
		//определение кнопки "Добавить ", "Сгенерировать билеты" и т.д
		JButton button_1 = new JButton("Добавить ");
		JButton button_2 = new JButton("Удалить");
		JButton button_3 = new JButton("Добавить");
		JButton button_4 = new JButton("Удалить");
		/* записываем в listDisciplin названия дисциплин*/
		ArrayList<String> listDisciplin =readDisciplin(db);
		/* записываем в listGroup названия групп*/
		ArrayList<String> listGroup =readGroup (db,disciplin);
		
		editorPane.addKeyListener(new KeyAdapter() {
			   public void keyTyped(KeyEvent e) {
				   char c = e.getKeyChar();
				      if ( Character.toString(c).matches("[^а-яА-Я]+") && c!=' ' && c!='-') {
				         e.consume();  // игнорим введенные буквы и пробел
				      }
				   }
				});
		
		/* Для того чтобы comboBox стал функциональным, необходимо присвоить обработчик событий, который будет отвечать за реагирование на события. 
		В нашем случае требуется идентифицировать событие нажатия на переключатель – путем щелчка мышью. Поэтому будет использоваться интерфейс " HierarchyListener ", предназначенный для обработки событий " HierarchyEvent ".  Тело интерфейса указывается ниже после фигурной скобки "{". */
		comboBox.setModel(new DefaultComboBoxModel(listDisciplin.toArray()));
		comboBox.addHierarchyListener(new HierarchyListener() {
			/* 	«HierarchyListener» имеет метод " hierarchyChanged" объекта " HierarchyEvent», который реализуется путем простого вызова обработчика событий hierarchyChanged. Ключевое слово public означает, что метод hierarchyChanged () доступен для любого другого класса Java. Ключевое слово void означает, что метод не возвращает данных в программу, которая его вызвала. */
			//при изменении записи в comboBox должны выводиться корректные вопросы и билеты
			public void hierarchyChanged(HierarchyEvent arg0) {
				/* getSelectedItem – получает выбранный элемент из comboBox. */
				disciplin= (String)(comboBox.getSelectedItem());
				/* Для задания блока программного кода, который требуется защитить от исключений, используется ключевое слово try. Сразу же после try-блока помещается блок catch, задающий тип исключения которое вы хотите обрабатывать. Исключение – это проблемная ситуация, возникающая по мере выполнения кода программы. Работает она так: 1. Выполняется код внутри блока try. 2.Если в нём ошибок нет, то блок catch(err) игнорируется, то есть выполнение доходит до конца try и потом прыгает через catch. 3.Если в нём возникнет ошибка, то выполнение try на ней прерывается, и управление прыгает в начало блока catch(err).*/
				try {
					//обновляем список вопросов на интерфейсе
					question();
					/*В случае возникновения исключений (ошибок) сработает блок catch
					 * функция не выполнится но программа останется работоспособной*/
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				} catch (SQLException e) {
					e.printStackTrace();
				}
				try {
					//обновляем список билетов на интерфейсе
					ticket();
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		});
		/* Аналогичным образом создаем прослушиватель событий и обработку события Listener*/
		comboBox.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
            	try {
					question();
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				} catch (SQLException e) {
					e.printStackTrace();
				}
				try {
					ticket();
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				} catch (SQLException e) {
					e.printStackTrace();
				}
            }
        });
		/* тут мы создаем прослушиватели и обработчики событий comboBox_1 */
		comboBox_1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
            	try {
					question();
					ticket();
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
					} 
            	catch (SQLException e) {
					e.printStackTrace();
				}
            }
        });
		
		comboBox_1.addHierarchyListener(new HierarchyListener() {
			public void hierarchyChanged(HierarchyEvent arg0) {
				try {
					ticket();
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		});
		
		comboBox_1.setModel(new DefaultComboBoxModel(listGroup.toArray()));
		// обрабатываем событие нажатие на кнопку добавления дисциплины
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//создаем диалоговое окно
				FrameAddDialog dialog = new FrameAddDialog();
				//задаем окну стандартные возможности свернуть и закрыть
				dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
				//делаем окно видимым
				dialog.setVisible(true);
				// после того как пользователь ввел необходимую информацию  в окне мы можем добавить новую записьь в бд и в comboBox
				try {
					//if (dialog.text!="") 
						if(dialog.text.trim().length() != 0) 
						
					{
					db.AddDisciplin(null, dialog.text);
					comboBox.addItem(dialog.text);
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		});
		
		//обрабатываем событие нажатие на кнопку "Удаление дисциплины"
		button_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
					try {
						//удаляем выбранную дисциплинуу из БД
						//getSelectedItem() = получение выбранного пункта
						db.DelDisciplin(null, (String)(comboBox.getSelectedItem()));
						//удаляем выбранную дисциплинуу из comboBox
						//removeItem удаление пункта
						comboBox.removeItem(comboBox.getSelectedItem());
					} catch (SQLException e) { 
						e.printStackTrace();
					}
			}
		});
		// аналогично описываем функционал кнопок добавления и удаления групп
		button_3.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				FrameAddDialog dialog = new FrameAddDialog();
				dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
				dialog.setVisible(true);
				try {
					if(dialog.text.trim().length() != 0) 
					{
						db.AddGroup(null, dialog.text);
						comboBox_1.addItem(dialog.text);
					}
					
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		button_4.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
					try {
						db.DelGroup(null, (String)(comboBox_1.getSelectedItem()));
						comboBox_1.removeItem(comboBox_1.getSelectedItem());
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
			}
		});
		// в коде ниже происходит добавлениеобъектов в окно пользователя , задание формата , стиля , расположения и т.д.
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 76, GroupLayout.PREFERRED_SIZE)
						.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, 93, GroupLayout.PREFERRED_SIZE)
						.addComponent(button_1, GroupLayout.DEFAULT_SIZE, 123, Short.MAX_VALUE)
						.addComponent(button_2, GroupLayout.DEFAULT_SIZE, 123, Short.MAX_VALUE)
						.addComponent(label, GroupLayout.PREFERRED_SIZE, 63, GroupLayout.PREFERRED_SIZE)
						.addComponent(comboBox_1, GroupLayout.PREFERRED_SIZE, 93, GroupLayout.PREFERRED_SIZE)
						.addComponent(button_3, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 123, Short.MAX_VALUE)
						.addComponent(button_4, GroupLayout.DEFAULT_SIZE, 123, Short.MAX_VALUE))
					.addContainerGap())
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblNewLabel)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(button_1)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(button_2)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(label)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(comboBox_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(button_3)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(button_4)
					.addContainerGap(105, Short.MAX_VALUE))
		);
		panel.setLayout(gl_panel);
		panel_4.setLayout(new BorderLayout(0, 0));
		question() ;
		contentPane.setLayout(new MigLayout("", "[113px,fill][14.00px][337.00px][5px][10px]", "[322.00px][51.00px]"));
		contentPane.add(panel, "flowx,cell 0 0,alignx left,growy");
		JPanel panel_1 = new JPanel();
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setViewportView(list_1);
		contentPane.add(panel_1, "cell 2 0,grow");
		panel_1.setLayout(new MigLayout("", "[326px]", "[322px]"));
		panel_1.add(scrollPane_1, "cell 0 0,grow");
		JLabel label_1 = new JLabel("Билеты");
		scrollPane_1.setColumnHeaderView(label_1);
		JPanel panel_2 = new JPanel();// Панель содержит кнопки и editorPane для управления списком вопросов
		JButton button = new JButton("Удалить");
		JButton btnNewButton_1 = new JButton("Добавить");
		btnNewButton_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				 int idDisciplin = 0;
				try {
					idDisciplin = serchID(db,"Disciplin",(String)(comboBox.getSelectedItem()));
				} catch (ClassNotFoundException e1) {
					e1.printStackTrace();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
					String TextQuestion=editorPane.getText();
					
				try {
					if(TextQuestion.trim().length() != 0) 
					{
					db.AddQuestion(null, idDisciplin, TextQuestion);
					question();
					}
				} catch (SQLException e1) {
					e1.printStackTrace();
				} catch (ClassNotFoundException e1) {
					e1.printStackTrace();
				}
			}
			
		});
		button.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String TextQuestion="";
				int length=(list.getSelectedValue().split(" ")).length;
				for(int i=3;i<length;i++)
					if(i==length-1) TextQuestion=TextQuestion+(list.getSelectedValue().split(" "))[i];
					else TextQuestion=TextQuestion+(list.getSelectedValue().split(" "))[i]+" ";
				try {
					db.DelQuestion(null, TextQuestion);
					question();
					
				} catch (SQLException e1) {
					e1.printStackTrace();
				} catch (ClassNotFoundException e1) {
					e1.printStackTrace();
				}
			}
		});
		GroupLayout gl_panel_2 = new GroupLayout(panel_2);
		gl_panel_2.setHorizontalGroup(
			gl_panel_2.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_2.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel_2.createParallelGroup(Alignment.LEADING)
						.addComponent(btnNewButton_1, GroupLayout.PREFERRED_SIZE, 92, GroupLayout.PREFERRED_SIZE)
						.addComponent(button, GroupLayout.DEFAULT_SIZE, 92, Short.MAX_VALUE))
					.addGap(18)
					.addComponent(editorPane, GroupLayout.PREFERRED_SIZE, 310, GroupLayout.PREFERRED_SIZE))
		);
		gl_panel_2.setVerticalGroup(
			gl_panel_2.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_2.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel_2.createParallelGroup(Alignment.LEADING)
						.addComponent(editorPane, GroupLayout.DEFAULT_SIZE, 52, Short.MAX_VALUE)
						.addGroup(gl_panel_2.createSequentialGroup()
							.addComponent(btnNewButton_1)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(button)))
					.addContainerGap())
		);
		panel_2.setLayout(gl_panel_2);
		contentPane.add(panel_2, "flowx,cell 0 1,alignx left,aligny center");
		contentPane.add(panel_4, "flowx,cell 0 1,alignx right,aligny top");
		JLabel lblNewLabel_1 = new JLabel("Вопросы"); //определение JLabel «Вопросы»
		JScrollPane scrollPane = new JScrollPane();
		contentPane.add(scrollPane, "cell 0 0,grow");
		scrollPane.setViewportView(list);
		list.setSelectedIndex(0);
		scrollPane.setColumnHeaderView(lblNewLabel_1);
		JPanel panel_5 = new JPanel();
		JLabel label_2 = new JLabel("Колличесво билетов");//определение JLabel «Количество билетов»
		JLabel label_3 = new JLabel("Колличесво вопросов в билете");//определение JLabel «Количество вопросов в билете»
		JButton button_5 = new JButton("Сгенерировать билеты");
		// добавим функционал кнопки генерации билетов
		button_5.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				if(textField.getText().trim().length() == 0) 
				{
					JOptionPane.showMessageDialog(D, "Вы должны ввести число!", "Ошибка", JOptionPane.WARNING_MESSAGE);
					return;
				}
				if(textField_1.getText().trim().length() == 0) 
				{
					JOptionPane.showMessageDialog(D, "Вы должны ввести число!", "Ошибка", JOptionPane.WARNING_MESSAGE);
					return;
				}
				int numberOfTickets;
				int questionsInTickets;
				TicketOutputException("Билеты генерируются...");
				//получаем из текстого поля колличесво билетов
					Integer.parseInt(textField.getText());
				//получаем из текстого поля колличесво вопросов в билете
					Integer.parseInt(textField_1.getText());
				ArrayList<String> questions = null;
				ArrayList<ArrayList<String> > Ticket = null;
				try {
					//в questions запишем вопросы из которых будут составляться билеты
					 questions =readQuestionTextWHERE(db,serchID(db,"Disciplin",(String)(comboBox.getSelectedItem())));
				} catch (ClassNotFoundException e2) {
					e2.printStackTrace();
				} catch (SQLException e2) {
					e2.printStackTrace();
				}
				//получаем из текстого поля колличесво билетов
				 numberOfTickets =  Integer.parseInt(textField.getText());
				//получаем из текстого поля колличесво вопросов в билете
				 questionsInTickets = Integer.parseInt(textField_1.getText());
				//с помощью TicketGenerator.generator генерируем билеты и записываем их в массив Ticket
				 
				Ticket=TicketGenerator.generator(questions, numberOfTickets, questionsInTickets);
				
				
				if(Ticket!=null) {
					try {
						// сохраняем билеты в БД
						SaveTicketToDb(db,Ticket);
					} catch (ClassNotFoundException e1) {
						e1.printStackTrace();
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
					//Выводим билеты из массива на экран
					TicketOutput(Ticket);
					}
					else
					{
						JOptionPane.showMessageDialog(D, "Слишком мало вопросов!", "Ошибка", JOptionPane.WARNING_MESSAGE);
						try {
							ticket();
						} catch (ClassNotFoundException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						//TicketOutputException("Слишком мало вопросов!");
					}
				}
				
		});
		textField = new JTextField();
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		
		textField.addKeyListener(new KeyAdapter() {
			   public void keyTyped(KeyEvent e) {
				   char c = e.getKeyChar();
				      if ( Character.toString(c).matches("[^0-9]+") ) {
				         e.consume();  // игнорим введенные буквы и пробел
				      }
				   }
				});
		textField_1.addKeyListener(new KeyAdapter() {
			   public void keyTyped(KeyEvent e) {
				   char c = e.getKeyChar();
				      if ( Character.toString(c).matches("[^0-9]+")) {
				         e.consume();  // игнорим введенные буквы и пробел
				      }
				   }
				});

		GroupLayout gl_panel_5 = new GroupLayout(panel_5);
		gl_panel_5.setHorizontalGroup(
			gl_panel_5.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_5.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel_5.createParallelGroup(Alignment.LEADING)
						.addComponent(button_5)
						.addGroup(Alignment.TRAILING, gl_panel_5.createSequentialGroup()
							.addGroup(gl_panel_5.createParallelGroup(Alignment.LEADING)
								.addComponent(label_2)
								.addComponent(label_3))
							.addPreferredGap(ComponentPlacement.RELATED, 17, Short.MAX_VALUE)
							.addGroup(gl_panel_5.createParallelGroup(Alignment.LEADING, false)
								.addComponent(textField_1, 0, 0, Short.MAX_VALUE)
								.addComponent(textField, GroupLayout.DEFAULT_SIZE, 73, Short.MAX_VALUE))))
					.addContainerGap())
		);
		gl_panel_5.setVerticalGroup(
			gl_panel_5.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_5.createSequentialGroup()
					.addGap(6)
					.addComponent(button_5)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel_5.createParallelGroup(Alignment.BASELINE)
						.addComponent(label_2)
						.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(6)
					.addGroup(gl_panel_5.createParallelGroup(Alignment.BASELINE)
						.addComponent(label_3)
						.addComponent(textField_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		panel_5.setLayout(gl_panel_5);
		contentPane.add(panel_5, "cell 2 1,grow");
		
	}
}
