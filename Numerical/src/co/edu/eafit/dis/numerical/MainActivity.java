package co.edu.eafit.dis.numerical;

import co.edu.eafit.dis.numerical.views.*;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends Activity {

	private Intent intent;
	private DrawerLayout drawerLayout;
	private ListView drawer;
	private ActionBarDrawerToggle toggle;
	private static final String[] opciones = {"Incremental search",
											  "Bisection",
											  "Newton"};

	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		// Rescatamos el Action Bar y activamos el boton Home
		getActionBar().setDisplayHomeAsUpEnabled(true);
		getActionBar().setHomeButtonEnabled(true);

		// Declarar e inicializar componentes para el Navigation Drawer
		drawer = (ListView) findViewById(R.id.drawer);
		drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);

		// Declarar adapter y eventos al hacer click
		drawer.setAdapter(new ArrayAdapter<String> (
							this,
							android.R.layout.simple_list_item_1,
							android.R.id.text1,
							opciones));
		
		drawer.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				switch (arg2) {
					case 0:  //Posici�n 0 en el vector opciones en Incremental
						intent = new Intent(MainActivity.this,
											IncrementalSearchActivity.class);
						MainActivity.this.startActivity(intent);
						break;
					case 1:  //Posici�n 1 en el vector opciones es Bisection 
		                intent = new Intent(MainActivity.this,
		                					BisectionActivity.class);
		                MainActivity.this.startActivity(intent);
						break;
					case 2:  //Posici�n 2 en el vector opciones es Newton 
		                intent = new Intent(MainActivity.this,
		                					NewtonActivity.class);
		                MainActivity.this.startActivity(intent);
						break;					
					default:
						break;
				}
				drawerLayout.closeDrawers();

			}
		});

		// Sombra del panel Navigation Drawer
		drawerLayout.setDrawerShadow(R.drawable.drawer_shadow,
									 GravityCompat.START);

		// Integracion boton oficial
		toggle = new ActionBarDrawerToggle(
				this, // Activity
				drawerLayout, // Panel del Navigation Drawer
				R.drawable.ic_drawer, // Icono que va a utilizar
				R.string.app_name, // Descripcion al abrir el drawer
				R.string.hello_world // Descripcion al cerrar el drawer
		) {
			public void onDrawerClosed(View view) {
				// Drawer cerrado
				getActionBar().setTitle(getResources()
										.getString(R.string.app_name));
				invalidateOptionsMenu();
			}

			public void onDrawerOpened(View drawerView) {
				// Drawer abierto
				getActionBar().setTitle("Methods");
				invalidateOptionsMenu(); 
			}
		};

		drawerLayout.setDrawerListener(toggle);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		if (toggle.onOptionsItemSelected(item)) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	// Activamos el toggle con el icono
	@Override
	protected void onPostCreate(Bundle savedInstanceState) {
		super.onPostCreate(savedInstanceState);
		toggle.syncState();
	}
}
