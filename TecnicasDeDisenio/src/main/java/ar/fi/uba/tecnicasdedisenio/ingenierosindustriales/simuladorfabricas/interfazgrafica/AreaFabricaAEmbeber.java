package ar.fi.uba.tecnicasdedisenio.ingenierosindustriales.simuladorfabricas.interfazgrafica;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CTabFolder;
import org.eclipse.swt.custom.CTabItem;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;

import ar.fi.uba.tecnicasdedisenio.ingenierosindustriales.simuladorfabricas.interfazgrafica.fabrica.ConstructorDeFabricas;
import ar.fi.uba.tecnicasdedisenio.ingenierosindustriales.simuladorfabricas.interfazgrafica.fabrica.DibujanteDeCintas;
import ar.fi.uba.tecnicasdedisenio.ingenierosindustriales.simuladorfabricas.interfazgrafica.fabrica.DibujanteDeMaquinas;
import ar.fi.uba.tecnicasdedisenio.ingenierosindustriales.simuladorfabricas.interfazgrafica.fabrica.DibujanteDeMateriaPrima;
import ar.fi.uba.tecnicasdedisenio.ingenierosindustriales.simuladorfabricas.interfazgrafica.fabrica.EspacioFabril;
import ar.fi.uba.tecnicasdedisenio.ingenierosindustriales.simuladorfabricas.lineaproduccion.tipomaquina.TipoMaquinaPlancha;
import ar.fi.uba.tecnicasdedisenio.ingenierosindustriales.simuladorfabricas.productos.Producto;
import ar.fi.uba.tecnicasdedisenio.ingenierosindustriales.simuladorfabricas.productos.ValidadorProductos;

public class AreaFabricaAEmbeber {

	//private Shell shellAreaDibujo = null;  //  @jve:decl-index=0:visual-constraint="109,17"
	private Canvas canvas = null;  //  @jve:decl-index=0:visual-constraint="263,47"
	private Group groupControl = null;
	private Button buttonCinta = null;
	private Button buttonMaquina = null;
	private Composite compositeControles = null;
	private Boolean dibujar = false;
	private Button buttonMateriaPrima = null;
	private Combo comboMP = null;
	private Combo comboMaquina = null;
	private ConstructorDeFabricas constructorDeFabricas;
	private EspacioFabril espacioFabril;
	private ValidadorProductos validadorProd = ValidadorProductos.instancia();

	/**
	 * This method initializes comboMP
	 *
	 */
	private void createComboMP() {
		comboMP = new Combo(groupControl, SWT.NONE);
		cargarComboMateriaPrimas();
		comboMP.setEnabled(false);
		comboMP.select(0);
		comboMP.addSelectionListener(new org.eclipse.swt.events.SelectionListener() {
			public void widgetSelected(org.eclipse.swt.events.SelectionEvent e) {
				System.out.println("Materia Prima Seleccionada= "+ comboMP.getText());
				elegirMP();
			}
			public void widgetDefaultSelected(org.eclipse.swt.events.SelectionEvent e) {
			}
		});

	}

	protected void cargarComboMateriaPrimas() {
		for(int i=0; i<10; i++){
			comboMP.add(new String("Materia Prima  " + i));
		}
		comboMP.setItems(validadorProd.toString().split(",", 0));
		comboMP.setItems(validadorProd.getAll());
		comboMP.setText(comboMP.getItem(0));
	}

	/**
	 * This method initializes comboMaquina
	 *
	 */
	private void createComboMaquina() {
		comboMaquina = new Combo(groupControl, SWT.NONE);
		cargarMaquinas();
		comboMaquina.select(0);
		comboMaquina.setEnabled(false);
		comboMaquina.addSelectionListener(new org.eclipse.swt.events.SelectionListener() {
			public void widgetSelected(org.eclipse.swt.events.SelectionEvent e) {
				System.out.println("Maquina Seleccionada= "+ comboMaquina.getText());
				elegirMaquina();
			}
			public void widgetDefaultSelected(org.eclipse.swt.events.SelectionEvent e) {
			}
		});
	}

	private void cargarMaquinas() {
		// TODO Auto-generated method stub
		for(int i=0; i<10; i++){
			comboMaquina.add("Maquina " + i);
		}
		comboMaquina.setText(comboMaquina.getItem(0));
	}

	private void elegirMP() {
		constructorDeFabricas.setDibujante(
				new DibujanteDeMateriaPrima(
						espacioFabril,
						new Producto(validadorProd, comboMP.getText(), 0F), comboMP.getText()));
	}

	private void elegirMaquina() {
		constructorDeFabricas.setDibujante(
				new DibujanteDeMaquinas(espacioFabril, new TipoMaquinaPlancha()));
	}
	
	public void load(CTabFolder cTabFolder) {
		// TODO Auto-generated method stub
		createShellAreaDibujo(cTabFolder);
	}
	
	public void run() {
		Display display =new Display();
		Shell shell = new Shell(display);
		GridData gridData = new GridData();
		GridLayout gridLayout = new GridLayout();
		gridLayout.numColumns = 2;
		gridData.grabExcessHorizontalSpace = true;
		gridData.horizontalAlignment = GridData.FILL;
		gridData.verticalAlignment = GridData.FILL;
		gridData.grabExcessVerticalSpace = true;
		CTabFolder cTabFolder = new CTabFolder(shell, SWT.NONE);
		cTabFolder.setLayoutData(gridData);
		CTabItem cTabItem = new CTabItem(cTabFolder, SWT.NONE);
		cTabItem.setText("Fabrica");
		createShellAreaDibujo(cTabFolder);
		cTabItem.setControl(compositeControles);

		shell.setLayout(gridLayout);
		shell.setSize(new Point(399, 316));
		shell.setVisible(true);

		while (!shell.isDisposed()) {
			if (!display.readAndDispatch())
				display.sleep();
		}
		display.dispose();
	}

	/**
	 * This method initializes shellAreaDibujo
	 * @param shellAreaDibujo
	 * @param display
	 * @param cTabItem
	 *
	 */
	private void createShellAreaDibujo(CTabFolder cTabFolder) {
		//GridLayout gridLayout = new GridLayout();
		//gridLayout.numColumns = 2;
		createCompositeControles(cTabFolder);
		createCanvas(compositeControles);
	}

	/**
	 * This method initializes canvas
	 * @param shellAreaDibujo
	 *
	 */
	private void createCanvas(Composite shellAreaDibujo) {
		GridData gridData = new GridData();
		gridData.horizontalAlignment = GridData.FILL;
		gridData.grabExcessHorizontalSpace = true;
		gridData.grabExcessVerticalSpace = true;
		gridData.verticalAlignment = GridData.FILL;
		canvas = new Canvas(shellAreaDibujo, SWT.BORDER);
		canvas.setLayout(new FillLayout());
		canvas.setSize(new Point(268, 259));
		canvas.setLayoutData(gridData);
		espacioFabril = new EspacioFabril(canvas);
		constructorDeFabricas = new ConstructorDeFabricas(new DibujanteDeCintas(espacioFabril));
		canvas.addListener (SWT.MouseDown, constructorDeFabricas);
		canvas.addListener (SWT.MouseUp, constructorDeFabricas);
		canvas.addListener (SWT.MouseMove, constructorDeFabricas);
	}

	/**
	 * This method initializes groupControl
	 *
	 */
	private void createGroupControl() {
		GridData gridData3 = new GridData();
		gridData3.horizontalAlignment = GridData.FILL;
		gridData3.verticalAlignment = GridData.CENTER;
		GridData gridData2 = new GridData();
		gridData2.horizontalAlignment = GridData.FILL;
		gridData2.verticalAlignment = GridData.CENTER;
		GridLayout gridLayout1 = new GridLayout();
		gridLayout1.numColumns = 1;
		GridData gridData1 = new GridData();
		gridData1.horizontalAlignment = GridData.FILL;
		gridData1.verticalAlignment = GridData.CENTER;
		groupControl = new Group(compositeControles, SWT.NONE);
		groupControl.setText("Controles");
		groupControl.setLayout(gridLayout1);
		buttonCinta = new Button(groupControl, SWT.TOGGLE);
		buttonCinta.setText("Linea");
		buttonCinta.setLayoutData(gridData1);
		buttonMateriaPrima = new Button(groupControl, SWT.TOGGLE);
		buttonMateriaPrima.setText("Materia Prima");
		buttonMateriaPrima.setLayoutData(gridData3);
		createComboMP();
		buttonMaquina = new Button(groupControl, SWT.TOGGLE);
		buttonMaquina.setText("Maquina");
		buttonMaquina.setLayoutData(gridData2);
		createComboMaquina();
		buttonCinta.addSelectionListener(new org.eclipse.swt.events.SelectionAdapter() {
			public void widgetSelected(org.eclipse.swt.events.SelectionEvent e) {
				System.out.println("Paso dibujar cintas");
				constructorDeFabricas.setDibujante(new DibujanteDeCintas(espacioFabril));
				deseleccionarControles();
			}
		});
		buttonMateriaPrima
		.addSelectionListener(new org.eclipse.swt.events.SelectionAdapter() {
			public void widgetSelected(org.eclipse.swt.events.SelectionEvent e) {
				System.out.println("Paso a dibujar materia prima");
				deseleccionarControles();
				comboMP.setEnabled(true);
				elegirMP();
			}
		});
		buttonMaquina.addSelectionListener(new org.eclipse.swt.events.SelectionAdapter() {
			public void widgetSelected(org.eclipse.swt.events.SelectionEvent e) {
				System.out.println("Paso a dibujar maquinas");
				deseleccionarControles();
				comboMaquina.setEnabled(true);
				elegirMaquina();
			}
		});


	}

	public void DibujarLinea(){
		this.setDibujar(!this.getDibujar());
	}

	/**
	 * This method initializes compositeControles
	 * @param shellAreaDibujo
	 *
	 */
	private void createCompositeControles(CTabFolder cTabFolder) {
		compositeControles = new Composite(cTabFolder, SWT.NONE);
		compositeControles.setLayout(new GridLayout());
		createGroupControl();
	}

	public void setDibujar(Boolean dibujar) {
		this.dibujar = dibujar;
	}

	public Boolean getDibujar() {
		return dibujar;
	}

	public Canvas getCanvas() {
		return canvas;
	}

	private void deseleccionarControles() {
		buttonCinta.setSelection(false);
		buttonMaquina.setSelection(false);
		buttonMateriaPrima.setSelection(false);
		comboMP.setEnabled(false);
		comboMaquina.setEnabled(false);
	}

	public Composite getCompositeControles() {
		return compositeControles;
	}



}

