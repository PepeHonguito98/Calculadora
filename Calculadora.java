import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Calculadora extends JFrame implements ActionListener {
	
	private boolean dSigno = false, dPunto = false, dNumero = false, dCalculoPrevio = false, dPositivoNegativo = false, dPorcentaje = false; //Detectores
	private String gEcuacion = "", gValorIngresado = "", gValorObtenido = "", gValorEnPantalla  = "", gEntero = "", gDecimales = "", gSigno = "", gPunto = "", gPorcentaje = ""; //Graficos
	
	private JTextArea pantalla;
	private JButton b0, b1, b2, b3, b4, b5, b6, b7, b8, b9;
	private JButton bSuma, bResta, bMultiplicacion, bDivision, bSeno, bCoseno, bLogaritmoNatural, bRaizCuadrada, bResultado, bPunto, bPositivoNegativo, bPorcentaje, bBorrar;
	
	private static int anchoMarco = 230, altoMarco = 500;
	
	public static void main(String[] args) {
		
		Calculadora marco = new Calculadora();
		marco.setSize(anchoMarco, altoMarco);
		marco.crearGUI();
		marco.setVisible(true);
		
	}
	
	private void botones() {
		
		b0 = new JButton("0");
		b1 = new JButton("1");
		b2 = new JButton("2");
		b3 = new JButton("3");
		b4 = new JButton("4");
		b5 = new JButton("5");
		b6 = new JButton("6");
		b7 = new JButton("7");
		b8 = new JButton("8");
		b9 = new JButton("9");
		
		bSuma = new JButton("+");
		bResta  = new JButton("-");
		bMultiplicacion = new JButton("*");
		bDivision = new JButton ("/");
		bSeno = new JButton("Sen");
		bCoseno = new JButton("Cos");
		bLogaritmoNatural = new JButton("Ln");
		bRaizCuadrada = new JButton("Raiz");
		bResultado = new JButton("=");
		bPunto = new JButton(".");
		bPositivoNegativo = new JButton("+/-");
		bPorcentaje = new JButton("%");
		bBorrar = new JButton("C");
		
		b0.addActionListener(this);
		b1.addActionListener(this);
		b2.addActionListener(this);
		b3.addActionListener(this);
		b4.addActionListener(this);
		b5.addActionListener(this);
		b6.addActionListener(this);
		b7.addActionListener(this);
		b8.addActionListener(this);
		b9.addActionListener(this);
		
		bSuma.addActionListener(this);
		bResta.addActionListener(this);
		bMultiplicacion.addActionListener(this);
		bDivision.addActionListener(this);
		bSeno.addActionListener(this);
		bCoseno.addActionListener(this);
		bLogaritmoNatural.addActionListener(this);
		bRaizCuadrada.addActionListener(this);
		bResultado.addActionListener(this);
		bPunto.addActionListener(this);
		bPositivoNegativo.addActionListener(this);
		bPorcentaje.addActionListener(this);
		bBorrar.addActionListener(this);
		
	}
	private void pantalla() {
		
		pantalla = new JTextArea(2,15);
		
	}
	private void IngresarNumero(String valor) {
		
		dNumero = true;
		dSigno = true;
		
		if(dPunto) { gDecimales = gDecimales + valor; }
		else { gEntero = gEntero + valor; }
		
		actualizar();
		
	}
	private void actualizar() {
		
		valorEnPantalla();
		pantalla.setText(gEcuacion + "\n" + gValorEnPantalla);
		
	}
	private void valorEnPantalla() {
		
		interruptores();
		
		gValorIngresado = gSigno + gEntero + gPunto + gDecimales + gPorcentaje;
		
		if(dCalculoPrevio) { gValorEnPantalla = gSigno + gValorObtenido + gPorcentaje; }
		else { gValorEnPantalla =  gValorIngresado; }
		
	}
	private void interruptores() {
		
		if(dPunto) { gPunto = "."; }
		else { gPunto = ""; }
		if(dPositivoNegativo && dNumero) { gSigno = "-"; }
		else { gSigno = ""; }
		if(dPorcentaje && dNumero) { gPorcentaje = "%"; }
		else { gPorcentaje = ""; }
		
	}
	private void valorObtenido(int a) {
		
		if(gValorEnPantalla.equals("")) {  }
		else {
			
			if(dPorcentaje) { gValorIngresado = gSigno + gEntero + gPunto + gDecimales; }
			
			Double x = Double.parseDouble(gValorIngresado);
			
			switch (a) {
				case 1: x = Math.sin(x);  break;
				case 2: x = Math.cos(x);  break;
				case 3: x = Math.log(x);  break;
				case 4: x = Math.sqrt(x); break;
			}
			
			if(x < 0) { x = Math.abs(x); dPositivoNegativo = true; }
			
			dCalculoPrevio = true;
			gValorObtenido = Double.toString(x);
			actualizar();
		}
	}
	private void Limpiar() {
		
		dSigno = false;
		dPunto = false;
		dNumero = false;
		dCalculoPrevio = false;
		dPositivoNegativo = false;
		dPorcentaje = false;
		
		gValorIngresado = "";
		gValorObtenido = "";
		gValorEnPantalla = "";
		gEntero = "";
		gDecimales = "";
		
		actualizar();
		
	}
	private void anotarEnPantalla(String a) {
		
		if(dNumero) { gEcuacion = gEcuacion + gValorEnPantalla + a;
		
		Limpiar();
		actualizar();
		
		}
	}
	private void Borrar() {
		
		gEcuacion = "";
		Limpiar();
		
	}
	
	private void crearGUI() {
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		Container ventana = getContentPane();
		ventana.setLayout(new FlowLayout());
		
		pantalla();
		botones();
		
		ventana.add(pantalla);
		ventana.add(b1);
		ventana.add(b2);
		ventana.add(b3);
		ventana.add(bSuma);
		ventana.add(b4);
		ventana.add(b5);
		ventana.add(b6);
		ventana.add(bResta);
		ventana.add(b7);
		ventana.add(b8);
		ventana.add(b9);
		ventana.add(bMultiplicacion);
		ventana.add(b0);
		ventana.add(bPunto);
		ventana.add(bPositivoNegativo);
		ventana.add(bDivision);
		ventana.add(bResultado);
		ventana.add(bSeno);
		ventana.add(bCoseno);
		ventana.add(bLogaritmoNatural);
		ventana.add(bRaizCuadrada);
		ventana.add(bBorrar);
		ventana.add(bPorcentaje);

	}

	public void actionPerformed(ActionEvent evento) {
		
		Object x = evento.getSource();
		
		if(x == b0)   { IngresarNumero("0"); }
		if(x == b1)   { IngresarNumero("1"); }
		if(x == b2)   { IngresarNumero("2"); }
		if(x == b3)   { IngresarNumero("3"); }
		if(x == b4)   { IngresarNumero("4"); }
		if(x == b5)   { IngresarNumero("5"); }
		if(x == b6)   { IngresarNumero("6"); }
		if(x == b7)   { IngresarNumero("7"); }
		if(x == b8)   { IngresarNumero("8"); }
		if(x == b9)   { IngresarNumero("9"); }
		
		if(x == bPunto)            { dPunto = true; actualizar(); }
		
		if(x == bPositivoNegativo) { 
			
			if(dPositivoNegativo) { dPositivoNegativo = false; }
			else { dPositivoNegativo = true; }
			actualizar(); 
			
		}
		if(x == bPorcentaje)       {
			
			if(dPorcentaje) { dPorcentaje = false; }
			else { dPorcentaje = true; }
			actualizar(); 
			
		}
		
		if(x == bSuma)           { anotarEnPantalla(" + "); }
		if(x == bResta)          { anotarEnPantalla(" - "); }
		if(x == bMultiplicacion) { anotarEnPantalla(" * "); }
		if(x == bDivision)       { anotarEnPantalla(" / "); }
		
		if(x == bSeno)             { valorObtenido(1); }
		if(x == bCoseno)           { valorObtenido(2); }
		if(x == bLogaritmoNatural) { valorObtenido(3); }
		if(x == bRaizCuadrada)     { valorObtenido(4); }
		
		if(x == bResultado) { anotarEnPantalla(" = "); }
		if(x == bBorrar)    { Borrar(); }
		
	}
	
}
