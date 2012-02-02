
import java.awt.event.*;
import java.io.*;
import java.text.*;
import javax.swing.*;
import java.util.*; 
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Container;
import datastructures.*; 
import testSortCol.*;

/**
 * Detta �r en frame varmed man kan testa olika sorterade samlingar.
 * Via framen kan man v�lja en textfil, f�r vilken man kan skapa
 * en referenslista. Referenslistan ber�knas i form av en map,
 * som har ut�kats med en r�knare f�r antalet j�mf�relser som
 * beh�vs av de ord som ing�r i texten. Efter ber�kningen
 * presenteras antalet j�mf�relser av nycklar som beh�vdes, den tid
 * det tog och hela referenslistan.
 */
public class TestFrame extends JFrame implements ActionListener {

  Scanner textfil = new Scanner( System.in );
  File indata = null;
  JFileChooser texten = new JFileChooser(".");

    /*  ***** H�r skall ni definiera era samlingar !!!  ***** */   
   
    CollectionWithGet<TestMapWithCounter.TestMapEntry<String,List<Integer>>>
	containerSLC   =   new SortedLinkedCollection<TestMapWithCounter.TestMapEntry<String,List<Integer>>>(),
	containerBST   =   new BSTwithGet<TestMapWithCounter.TestMapEntry<String,List<Integer>>>(),
        containerAVL   =   new AVLwithGet<TestMapWithCounter.TestMapEntry<String,List<Integer>>>();
//      containerSplay =   ;
    
    /*    **************************************************  */   

  TestMapWithCounter<String,List<Integer>> slcMap =
     new TestMapWithCounter<String,List<Integer>>(containerSLC);

  TestMapWithCounter<String,List<Integer>> bstMap =
     new TestMapWithCounter<String,List<Integer>>(containerBST);

  TestMapWithCounter<String,List<Integer>> avlMap =
     new TestMapWithCounter<String,List<Integer>>(containerAVL);

//  TestMapWithCounter<String,List<Integer>> splayMap =
//     new TestMapWithCounter<String,List<Integer>>(containerSplay);

  TestMapWithCounter<String,List<Integer>>  map =  bstMap;



  JRadioButton list  = new JRadioButton("SLC",false);
  JRadioButton bst   = new JRadioButton("BST",true);
  JRadioButton avl   = new JRadioButton("AVL",false);
  JRadioButton splay = new JRadioButton("Splay",false);
  

  JButton text     = new JButton(" V�lj fil");
  JButton berakna  = new JButton(" Ber�kna refs "); 
  JLabel  antjfr   = new JLabel("--", JLabel.CENTER);
  JLabel  millisec = new JLabel("--", JLabel.CENTER);

  DefaultListModel listModel = new DefaultListModel();
  JList            ordlistan = new JList(listModel);
  JScrollPane      delList   = new JScrollPane(ordlistan);
  NumberFormat     nf        = NumberFormat.getInstance(Locale.UK);

  class RadioLyssnare implements ActionListener {
    public void actionPerformed(ActionEvent e) {
	if ( e.getSource() == list )
	    map = slcMap;
        else if ( e.getSource() == bst )
	    map = bstMap;
        else if ( e.getSource() == avl )
	    map = avlMap;
//        else  
//	    map = splayMap;
    }
  }

  public TestFrame() {

    nf.setGroupingUsed(true);
    setBackground(Color.yellow);
    setDefaultCloseOperation( EXIT_ON_CLOSE );
    nf.setMaximumFractionDigits(2);
    Container content = getContentPane();

    JPanel radioknapparna = new JPanel(new GridLayout(1,4));
    ButtonGroup radiogrupp = new ButtonGroup();
    radiogrupp.add(list);
    radiogrupp.add(bst);
    radiogrupp.add(avl);
    radiogrupp.add(splay);
    RadioLyssnare rl = new RadioLyssnare();
    list.addActionListener(rl);
    bst.addActionListener(rl);
    avl.addActionListener(rl);
    splay.addActionListener(rl);
    content.add(  BorderLayout.NORTH, radioknapparna);
    radioknapparna.add(list);
    radioknapparna.add(bst);
    radioknapparna.add(avl);
    radioknapparna.add(splay);
    

    JPanel knapparna = new JPanel(new GridLayout(2,3));
    content.add(knapparna, BorderLayout.CENTER );
    knapparna.add(text);
    knapparna.add(new JLabel("Antal j�mf�relser", JLabel.CENTER));
    knapparna.add(new JLabel("Antal millisek", JLabel.CENTER));
    knapparna.add(berakna);
    knapparna.add(antjfr);
    knapparna.add(millisec);
    text.addActionListener(this);
    text.setBackground(Color.blue);
    text.setForeground(Color.yellow);
    berakna.addActionListener(this);
    berakna.setBackground(Color.blue);
    berakna.setForeground(Color.yellow);

    setSize(600,300);
    listModel.addElement(" V�lj en fil genom att klicka ovan och " );
    listModel.addElement(" klicka sedan p� ber�kna. " );
    listModel.addElement(" Eller klicka p� Ber�kna skriv sedan in " );
    listModel.addElement(" text via teminalrutan och avsluta med " );
    listModel.addElement(" <CTRL>-d p� ny rad" );
    
    content.add(delList, BorderLayout.SOUTH );
    setVisible(true);
  }

  public void actionPerformed(ActionEvent e) {
    if (e.getSource() == text) {
      if (textfil != null)
	textfil.close();
      int retVal = texten.showOpenDialog(this);
      if (retVal == JFileChooser.APPROVE_OPTION ) {
	  indata = texten.getSelectedFile();
          text.setText(" Testfilen: " + indata.getName());
          antjfr.setText( "--" );
          millisec.setText( "--" );
      }
      else
         System.err.println("retVal = " + retVal);
    }
    else if ( e.getSource() == berakna ) {
	map.clear();
        try{ if (indata != null )
                textfil = new Scanner(indata);
             map.clear();
             map.resetCounter();
             long millis = System.currentTimeMillis();
             Referenslista.findRefs( textfil, map );
             millis = System.currentTimeMillis() -millis;
             millisec.setText( nf.format(millis) + " ms." );
             listModel.clear();
             antjfr.setText( nf.format(map.getCounter()) + " st." );
	     for( Map.Entry<String,List<Integer>> me : map.entrySet() )
	         listModel.addElement( me.getKey() + "  " + me.getValue() );
	     textfil.close();
	}
        catch( FileNotFoundException fnfe) { 
            listModel.addElement( 
               "Filen " + (indata != null ? indata.getName() : "System.in" ) 
               + " kunde inte �ppnas !!" ); 
	} 
    }
  }

  public static void main(String[] args) {
    new TestFrame();
  }
}
