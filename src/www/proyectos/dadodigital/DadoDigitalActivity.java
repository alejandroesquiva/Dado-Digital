/*Alejandro Esquiva Rodríguez 
 * 
 * Dado Digital
 * 
 * Esta aplicación simulará un dado en nuestro Android.
 * 
 * 
 * tengo que averiguar como llamar a un objeto creado manualmente desde el evento onclick
 */

package www.proyectos.dadodigital;

import java.util.Date;
import java.util.Random;
import java.util.Vector;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.Display;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.ads.AdRequest;
import com.google.ads.AdSize;
import com.google.ads.AdView;



public class DadoDigitalActivity extends Activity implements OnClickListener,OnItemSelectedListener {
    /** Called when the activity is first created. */
   
/************************************************************************************************************
 * Variables y constantes 
 ************************************************************************************************************/
	
	public static final String ID_ADMOB="a14f9163f749660";
	public static final int MULT_MILLIS=1000;	// el numero aleatorio se multiplicará por esto
	public static final int RANGO_RANDOM=5;		// es el rango de nuestro numero aleatorio
	public static final int ESCALADO_TIMER=100;	// es el escalado de la cuenta, cada cuanto cambia la imagen
	public static final int MARGEN_IMAGEN=30;
	
	private AdView adView;
	
	int res,numerosalido,numerotirada=1;	
    int numerodadoselegidos=2;
    int tamaño;
    int anchopantalla;
    int pulsadoimagenindividual=1;
    int sumatotal=0;
    
	long temp;
	Boolean estado=true,botontirar=false,imagenindividual=false;	
	ImageView tirar,registro,dado1,dado2,dado3,dado4,dado5,dado6,image;   
    Random r;     
    ScrollView scroll;
 
    Integer[] imagenesID={
    	R.drawable.d1,
    	R.drawable.d2,
    	R.drawable.d3,
    	R.drawable.d4, 
    	R.drawable.d5,
    	R.drawable.d6,   
    	};
    
   /* String dadouno=getString(R.string.undado);
    String dadodos=getString(R.string.undado);
    String dadotres=getString(R.string.undado);
    String dadocuatro=getString(R.string.undado);
    String dadocinco=getString(R.string.undado);
    String dadoseis=getString(R.string.undado);
    */
    
    String[] cadena={"Un dado","Dos Dados","Tres Dados","Cuatro Dados","Cinco Dados","Seis Dados"};
    
    String t="";
	String b="";
	String a="";
	String c="";
	String d="";
    
    Vector v= new Vector();
    Vector vdados=new Vector();
    Vector valores=new Vector();
    
    LinearLayout ldado;
    LinearLayout linear1;
    LinearLayout linear2;
    LinearLayout linear3;
    
    Display display;
    
    Spinner	spinner;
    
    
/************************************************************************************************************
* Programa Principal
************************************************************************************************************/ 
    
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        ArrayAdapter<String>adaptador=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,cadena);
        
/************************************************************************************************************
* Admob (Publicidad) 
************************************************************************************************************/ 
   	
    	
   	 // Crear la adView
       adView = new AdView(this, AdSize.BANNER,ID_ADMOB);

       // Buscar el LinearLayout suponiendo que se le haya asignado
       // el atributo android:id="@+id/mainLayout"
       LinearLayout layout = (LinearLayout)findViewById(R.id.linearLayout2);

       // Añadirle la adView
       layout.addView(adView);

       // Iniciar una solicitud genérica para cargarla con un anuncio
       adView.loadAd(new AdRequest());

/************************************************************************************************************
* Opciones de pantalla completa  
************************************************************************************************************/ 
      
     //  requestWindowFeature(Window.FEATURE_NO_TITLE);
      // getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        
/************************************************************************************************************
* Declaracion de los objetos 
************************************************************************************************************/ 
       
       tirar=(ImageView)findViewById(R.id.btirar);
       tirar.setOnClickListener(this);
       
       
       registro=(ImageView)findViewById(R.id.bregistro);
       registro.setOnClickListener(this);
       
       
       ldado=(LinearLayout)findViewById(R.id.linearLayout1);
       
       linear1=new LinearLayout(this);
       linear2=new LinearLayout(this);
       linear3=new LinearLayout(this);
       
       dado1 =new ImageView(this);
       dado2 =new ImageView(this);
       dado3 =new ImageView(this);
       dado4 =new ImageView(this);
       dado5 =new ImageView(this);
       dado6 =new ImageView(this);
       
       dado1.setId(11111);
       dado2.setId(22222);
       dado3.setId(33333);
       dado4.setId(44444);
       dado5.setId(55555);
       dado6.setId(66666);
       
       dado1.setOnClickListener(this);
       dado2.setOnClickListener(this);
       dado3.setOnClickListener(this);
       dado4.setOnClickListener(this);
       dado5.setOnClickListener(this);
       dado6.setOnClickListener(this);
       
       spinner=(Spinner)findViewById(R.id.spinner1);
       spinner.setOnItemSelectedListener(this);
       spinner.setAdapter(adaptador);

       
       
       
       ImageView Image=new ImageView(this);
       
       vdados.add(dado1);
       vdados.add(dado2);
       vdados.add(dado3);
       vdados.add(dado4);
       vdados.add(dado5);
       vdados.add(dado6);
       
/************************************************************************************************************
* Parametro de los layouts y de los contenedores
************************************************************************************************************/       
       
       LayoutParams params=new LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT);
       
       linear1.setLayoutParams(params);
       linear2.setLayoutParams(params);
       linear3.setLayoutParams(params);
      
       
       display = ((WindowManager) getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay();
       anchopantalla=display.getWidth();

      	linear1.addView(dado1);
        linear1.addView(dado2);
        linear2.addView(dado3);
        linear2.addView(dado4);
        linear3.addView(dado5);
        linear3.addView(dado6);
        
        linear1.setOrientation(LinearLayout.HORIZONTAL);
        linear2.setOrientation(LinearLayout.HORIZONTAL);
        linear3.setOrientation(LinearLayout.HORIZONTAL);
        
        ldado.addView(linear1);
        ldado.addView(linear2);
        ldado.addView(linear3);
        
       
       r=new Random();
       
       	linear1.setWeightSum(2);
		linear2.setWeightSum(2);
		linear3.setWeightSum(2);
		
       elegirnumerosdados(numerodadoselegidos);
          
       
    }
    
/************************************************************************************************************
*	Esto sirve para que no se desbloquee la pantalla
************************************************************************************************************/ 
    
	public void onResume() {
		
		super.onResume();
		getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);        
        
    }
    
/************************************************************************************************************
*	Funcion que muestra en la pantalla correctamente todos los dados
************************************************************************************************************/ 
    
    public void elegirnumerosdados(int dadoelegido){
	    	
	    	if(dadoelegido==1){
	    		tamaño=anchopantalla-MARGEN_IMAGEN;
	    		
	    		
	        	dado1.setLayoutParams(new LinearLayout.LayoutParams(tamaño,tamaño,1));
	        	dado2.setLayoutParams(new LinearLayout.LayoutParams(android.widget.LinearLayout.LayoutParams.WRAP_CONTENT,android.widget.LinearLayout.LayoutParams.WRAP_CONTENT,1));	        	
	        	dado3.setLayoutParams(new LinearLayout.LayoutParams(android.widget.LinearLayout.LayoutParams.WRAP_CONTENT,android.widget.LinearLayout.LayoutParams.WRAP_CONTENT,1));	        	
	        	dado4.setLayoutParams(new LinearLayout.LayoutParams(android.widget.LinearLayout.LayoutParams.WRAP_CONTENT,android.widget.LinearLayout.LayoutParams.WRAP_CONTENT,1));	       
	        	dado5.setLayoutParams(new LinearLayout.LayoutParams(android.widget.LinearLayout.LayoutParams.WRAP_CONTENT,android.widget.LinearLayout.LayoutParams.WRAP_CONTENT,1));	        	
	        	dado6.setLayoutParams(new LinearLayout.LayoutParams(android.widget.LinearLayout.LayoutParams.WRAP_CONTENT,android.widget.LinearLayout.LayoutParams.WRAP_CONTENT,1));
	        		
	    	}
	    	
			if(dadoelegido==2){
				tamaño=anchopantalla/2-MARGEN_IMAGEN;
		   	
			
	        	dado1.setLayoutParams(new LinearLayout.LayoutParams(tamaño,tamaño,2));
	        	dado2.setLayoutParams(new LinearLayout.LayoutParams(android.widget.LinearLayout.LayoutParams.WRAP_CONTENT,android.widget.LinearLayout.LayoutParams.WRAP_CONTENT,1));	        
	        	dado3.setLayoutParams(new LinearLayout.LayoutParams(tamaño,tamaño,2));	        	
	        	dado4.setLayoutParams(new LinearLayout.LayoutParams(android.widget.LinearLayout.LayoutParams.WRAP_CONTENT,android.widget.LinearLayout.LayoutParams.WRAP_CONTENT,1));	        	
	        	dado5.setLayoutParams(new LinearLayout.LayoutParams(android.widget.LinearLayout.LayoutParams.WRAP_CONTENT,android.widget.LinearLayout.LayoutParams.WRAP_CONTENT,1));	        
	        	dado6.setLayoutParams(new LinearLayout.LayoutParams(android.widget.LinearLayout.LayoutParams.WRAP_CONTENT,android.widget.LinearLayout.LayoutParams.WRAP_CONTENT,1));
			    		
			}
			if(dadoelegido==3){
				
				tamaño=anchopantalla/2-MARGEN_IMAGEN;
				
				
	        	dado1.setLayoutParams(new LinearLayout.LayoutParams(tamaño,tamaño,1));
	        	dado2.setLayoutParams(new LinearLayout.LayoutParams(tamaño,tamaño,1));	        	
	        	dado3.setLayoutParams(new LinearLayout.LayoutParams(tamaño,tamaño,2));	        	
	        	dado4.setLayoutParams(new LinearLayout.LayoutParams(android.widget.LinearLayout.LayoutParams.WRAP_CONTENT,android.widget.LinearLayout.LayoutParams.WRAP_CONTENT,1));	        	
	        	dado5.setLayoutParams(new LinearLayout.LayoutParams(android.widget.LinearLayout.LayoutParams.WRAP_CONTENT,android.widget.LinearLayout.LayoutParams.WRAP_CONTENT,1));	        	
	        	dado6.setLayoutParams(new LinearLayout.LayoutParams(android.widget.LinearLayout.LayoutParams.WRAP_CONTENT,android.widget.LinearLayout.LayoutParams.WRAP_CONTENT,1));
	        	
			}
			if(dadoelegido==4){
				
				tamaño=anchopantalla/2-MARGEN_IMAGEN;
	        	
			
	        	dado1.setLayoutParams(new LinearLayout.LayoutParams(tamaño,tamaño,1));
	        	dado2.setLayoutParams(new LinearLayout.LayoutParams(tamaño,tamaño,1));	        	
	        	dado3.setLayoutParams(new LinearLayout.LayoutParams(tamaño,tamaño,1));	        	
	        	dado4.setLayoutParams(new LinearLayout.LayoutParams(tamaño,tamaño,1));	        	
	        	dado5.setLayoutParams(new LinearLayout.LayoutParams(android.widget.LinearLayout.LayoutParams.WRAP_CONTENT,android.widget.LinearLayout.LayoutParams.WRAP_CONTENT,1));	        	
	        	dado6.setLayoutParams(new LinearLayout.LayoutParams(android.widget.LinearLayout.LayoutParams.WRAP_CONTENT,android.widget.LinearLayout.LayoutParams.WRAP_CONTENT,1));
			
			}
			if(dadoelegido==5){
				
				tamaño=anchopantalla/3-MARGEN_IMAGEN;
	        	
		
	        	dado1.setLayoutParams(new LinearLayout.LayoutParams(tamaño,tamaño,1));
	        	dado2.setLayoutParams(new LinearLayout.LayoutParams(tamaño,tamaño,1));	       
	        	dado3.setLayoutParams(new LinearLayout.LayoutParams(tamaño,tamaño,1));	     
	        	dado4.setLayoutParams(new LinearLayout.LayoutParams(tamaño,tamaño,1));	 
	        	dado5.setLayoutParams(new LinearLayout.LayoutParams(tamaño,tamaño,2));
	        	dado6.setLayoutParams(new LinearLayout.LayoutParams(android.widget.LinearLayout.LayoutParams.WRAP_CONTENT,android.widget.LinearLayout.LayoutParams.WRAP_CONTENT,1));
	        	
			}
			if(dadoelegido==6){
				
				tamaño=anchopantalla/3-MARGEN_IMAGEN;
	        	
				
	        	dado1.setLayoutParams(new LinearLayout.LayoutParams(tamaño,tamaño,1));
	        	dado2.setLayoutParams(new LinearLayout.LayoutParams(tamaño,tamaño,1));	        
	        	dado3.setLayoutParams(new LinearLayout.LayoutParams(tamaño,tamaño,1));	        	
	        	dado4.setLayoutParams(new LinearLayout.LayoutParams(tamaño,tamaño,1));	        	
	        	dado5.setLayoutParams(new LinearLayout.LayoutParams(tamaño,tamaño,1));	        	
	        	dado6.setLayoutParams(new LinearLayout.LayoutParams(tamaño,tamaño,1));
				
			}
			
			if(numerodadoselegidos!=2){
				for(int i=0;i<numerodadoselegidos;i++){
				    
				    ((ImageView) vdados.get(i)).setImageResource(R.drawable.d1);
					    
					}
		        	
					for(int i=6;numerodadoselegidos<i;i--){
					((ImageView) vdados.get(i-1)).setImageResource(R.drawable.vacio);
					}
			}else{
				((ImageView) vdados.get(0)).setImageResource(R.drawable.d1);
				((ImageView) vdados.get(1)).setImageResource(R.drawable.vacio);
				((ImageView) vdados.get(2)).setImageResource(R.drawable.d1);
				((ImageView) vdados.get(3)).setImageResource(R.drawable.vacio);
				((ImageView) vdados.get(4)).setImageResource(R.drawable.vacio);
				((ImageView) vdados.get(5)).setImageResource(R.drawable.vacio);
			}
			
     
	    }



 /************************************************************************************************************
 * Acciones al pulsar sobre los botones, imagenes
 ************************************************************************************************************/ 
	@Override
	public void onClick(View view) {
		// TODO Auto-generated method stub
/************************************************************************************************************
*  si se pulsa la imagen tirar o la imagen mostrar, se genera una secuencia aleatoria del dado 
************************************************************************************************************/ 
		sumatotal=0;
		
		
			if(view.getId()==R.id.btirar ){//|| view.getId()==R.id.mostrar){
				
			

					tirardados();
				}
				
				
						
			
		

/************************************************************************************************************
*  si se pulsa la imagen de registro, abrimos un dialogo e introducimos en un scrollview un textview con el 
*  contenido de un vector en que se guarda los registros
************************************************************************************************************/ 
		if(view.getId()==R.id.bregistro){
					
				
				mostrarregistro();
				
				
		}
	
		
	
			if(view.getId()==dado1.getId()){
				
				 tirardados();		
				
			}
			
			if(view.getId()==dado2.getId()){
				tirardados();
			}
			if(view.getId()==dado3.getId()){
				tirardados();
			}
			
			if(view.getId()==dado4.getId()){
				tirardados();
			}
			
			if(view.getId()==dado5.getId()){
				tirardados();
					
			}
			
			if(view.getId()==dado6.getId()){
				
				tirardados();
			}
			
		

		
		
		
		
		
	}
	
/************************************************************************************************************
 * Escribe un mensaje en la pantalla con el valor que has sacado.
 ************************************************************************************************************/ 

		 public void escribirmensaje(){
			 if(botontirar==true){
				 Toast mensaje= Toast.makeText(this, c, Toast.LENGTH_SHORT);
				 mensaje.show();
			 }
			
			 
		 }
		 
		 public void tirardados(){
		
			 if(estado=true){
					res=r.nextInt(RANGO_RANDOM);
					temp=res*MULT_MILLIS;
				       
				       if(temp<1000){
				    	   temp=temp+2000;
				       }
				       
						botontirar=true;
						spinner.setEnabled(false);
						tirar.setEnabled(false);
						dado1.setEnabled(false);
						dado2.setEnabled(false);
						dado3.setEnabled(false);
						dado4.setEnabled(false);
						dado5.setEnabled(false);
						dado6.setEnabled(false);
						
						numerotirada=v.size()+1;
				       
						Tiempo regresivo=new Tiempo(temp,ESCALADO_TIMER);
						regresivo.start();
				 }			 
				 
			 }
		 
		 public void mostrarregistro(){
			 
				t="";
				
	
			 
			 if(v.size()==0){
					
				 Toast mensaje= Toast.makeText(this, getString(R.string.registrovacio), Toast.LENGTH_LONG);
				 mensaje.show();	
				  
			}else{
				
				
					for(int i=v.size()-1;i>=0;i--){
						
						t=t+ v.get(i)+"\n";
						
					}


				
				Dialog d= new Dialog(this);
				d.setTitle(getString(R.string.registrodetiradas));
				TextView registro=new TextView(this);
				registro.setText(t);
				
				scroll=new ScrollView(this);
				scroll.addView(registro);
				d.setContentView(scroll);
				d.show();
			}
			 
			 
		 }

		 	
		

 		  
 		    
	




/************************************************************************************************************
*  Clase Tiempo que extiende de CountDownTimer, es la encargada de generar una cuenta regresiva en millis, 
*  la cual genera una interfaz para poder mostrar las imagenes cada un cierto preescalado 
************************************************************************************************************/ 
		 class Tiempo extends CountDownTimer{
			/*
			 * la variable millisInfuture Idica el tiempo en milis de la cuenta regresiva, la variable
			 * countDownInterval indica como quiere que contemos el tiempo, de 1 en 1 o de x en x
			 */
			public Tiempo(long millisInFuture, long countDownInterval) {
				super(millisInFuture, countDownInterval);
				 
	
				

			}

			@Override
			public void onFinish() {
				//Al terminar la cuenta		
				
				
				imagenindividual=false;
				estado=true;
				a="";
				b="";
				c="";
				sumatotal=0;
				
				dado1.setImageResource(imagenesID[(Integer)valores.get(0)-1]);//solucion al cambio sin sentido 

				spinner.setEnabled(true);
				tirar.setEnabled(true);
				
				dado1.setEnabled(true);
				dado2.setEnabled(true);
				dado3.setEnabled(true);
				dado4.setEnabled(true);
				dado5.setEnabled(true);
				dado6.setEnabled(true);
				
				
				Date hora=new Date();	
				
				
				if(numerodadoselegidos!=2){
					for(int i=0;i<numerodadoselegidos;i++){
						sumatotal=sumatotal+(Integer)valores.get(i);
						
						if((i+1)==numerodadoselegidos){
							b=b+(Integer)valores.get(i)+"";
						}else{
							b=b+(Integer)valores.get(i)+"+";
						}
					}
				}else{
					sumatotal=(Integer)valores.get(0)+(Integer)valores.get(2);
					b=(Integer)valores.get(0)+"+"+(Integer)valores.get(2);
					
				}
				
				
				
				
				if(numerodadoselegidos!=1){
					
					a=cadena[numerodadoselegidos-1]+": \n";
					d=", "+getString(R.string.hora)+": "+hora.getHours()+":"+hora.getMinutes()+":"+hora.getSeconds()+"\n";
					c=numerotirada+"º "+getString(R.string.tirada)+": " +b + " = "+ sumatotal;
					a=a+c+d;
					
					v.add(a);
					escribirmensaje();
					botontirar=false;
				}else{
					d=", "+getString(R.string.hora)+": "+hora.getHours()+":"+hora.getMinutes()+":"+hora.getSeconds()+"\n";
					a=cadena[numerodadoselegidos-1]+": \n";
					c=numerotirada+"º "+getString(R.string.tirada)+": "+sumatotal; 
					a=a+c+d;
					
					v.add(a);
					escribirmensaje();
					botontirar=false;
				}
		
			}

			@Override
			public void onTick(long millisUntilFinished) {
				// Accion que realizamos en cada preescalado				
								
				estado=false;
				
				
			   //  elegirnumerosdados(numerodadoselegidos);
				
				if(botontirar==true){
					
					if(numerodadoselegidos!=2){
					for(int i=0;i<numerodadoselegidos;i++){
						
					    Random x;
					    x=new Random();
					    numerosalido =x.nextInt(6);
					    
				    ((ImageView) vdados.get(i)).setImageResource(imagenesID[numerosalido]);
					  numerosalido++;
					  valores.add(i,numerosalido);
					   
					}

		        	
					for(int i=6;numerodadoselegidos<i;i--){
						((ImageView) vdados.get(i-1)).setImageResource(R.drawable.vacio);
					}
					
					}else{
						
					    Random x;
					    
					    x=new Random();
					    numerosalido =x.nextInt(6);
						((ImageView) vdados.get(0)).setImageResource(imagenesID[numerosalido]);
			   		  	numerosalido++;
			   		  	valores.add(0,numerosalido);
			   		  	
					    x=new Random();
					    numerosalido =x.nextInt(6);
						((ImageView) vdados.get(2)).setImageResource(imagenesID[numerosalido]);
			   		  	numerosalido++;
			   		  	
			   		  	
			   		  	try{
			   		  	valores.add(2,numerosalido);
			   		  	}catch(ArrayIndexOutOfBoundsException e){
			   		  		
			   		  	}
			   		  	

						((ImageView) vdados.get(1)).setImageResource(R.drawable.vacio);
						((ImageView) vdados.get(3)).setImageResource(R.drawable.vacio);
						((ImageView) vdados.get(4)).setImageResource(R.drawable.vacio);
						((ImageView) vdados.get(5)).setImageResource(R.drawable.vacio);
					}
					
					
					
				}
				
			/*	if(imagenindividual=true){
					Random x;
				    x=new Random();
				    numerosalido =x.nextInt(6);
				    ((ImageView) vdados.get(pulsadoimagenindividual-1)).setImageResource(imagenesID[numerosalido]);
				    sumatotal=numerosalido;
				}*/
				
				
			}
			
		 }
		  public void onDestroy() {
	   		    adView.destroy();
	   		    super.onDestroy();
	   		  }

		@Override
		public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2,
				long arg3) {
			// TODO Auto-generated method stub
			
			int posicion=spinner.getSelectedItemPosition();
			
			if(posicion==0){
				dibujar(posicion);
			}
			
			if(posicion==1){
				dibujar(posicion);
			}
			
			if(posicion==2){
				dibujar(posicion);
			}
			
			if(posicion==3){
				dibujar(posicion);
			}
			
			if(posicion==4){
				dibujar(posicion);
			}
			
			if(posicion==5){
				dibujar(posicion);
			}
			

			
		}

		@Override
		public void onNothingSelected(AdapterView<?> arg0) {
			// TODO Auto-generated method stub
			
		}
		
		public void dibujar(int posicion){
			numerodadoselegidos=posicion+1;
			elegirnumerosdados(numerodadoselegidos);
			
		}
		
		public boolean onCreateOptionsMenu(Menu menu){
			super.onCreateOptionsMenu(menu);
			MenuInflater inflater=getMenuInflater();
			inflater.inflate(R.menu.main, menu);
			return true;
		}
		
		public boolean onOptionsItemSelected(MenuItem item){
			
			if(item.getItemId()==R.id.itemregistro){
				
				mostrarregistro();
			}
			
			if(item.getItemId()==R.id.acercade){
				
					String acerca="Aplicacion creada por:\n\nAlejandro Esquiva Rodríguez.\n\nPara informar de cualquier Bug o sugerencia escriban un email a:\n\nalexbigastro@gmail.com\n\n\nGracias!!!  ";
					
					
				 Dialog d= new Dialog(this);
					
				 d.setTitle(getString(R.string.acerdade));
					TextView registro=new TextView(this);
					registro.setText(acerca);
					
					scroll=new ScrollView(this);
					scroll.addView(registro);
					d.setContentView(scroll);
					d.show();
				
			}
			
			if(item.getItemId()==R.id.salir){
				finish();
			}
			return true;
		}
		

	}



		
		


			
	

