package co.edu.eafit.dis.numerical.views;

import co.edu.eafit.dis.numerical.R;
import co.edu.eafit.dis.numerical.utils.InputChecker;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;
import android.view.View;

public class MatrixUnknownsActivity extends Activity {

  private EditText numberOfUnknowns;
  private TextView subsectionTextView;
  private String subSectionName;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_matrix_unknowns);

    // Activar el bot�n de ir atr�s en el action bar
    getActionBar().setDisplayHomeAsUpEnabled(true);

    subSectionName = getIntent().getExtras().getString(
        getResources().getString(R.string.text_key_subsection_name));

    numberOfUnknowns = (EditText) findViewById(R.id.number_of_unkwnowns);
    subsectionTextView = (TextView) findViewById(R.id.subsection_title);
    setUpView();
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    switch (item.getItemId()) {
      case android.R.id.home:
        finish();
        return true;
      default:
        return super.onOptionsItemSelected(item);
    }
  }

  public void setUpView() {
    subsectionTextView.setText(subSectionName);
  }

  public void continueToMethods(View view) {
    String unknowns = numberOfUnknowns.getText().toString();
    if (unknowns.trim().isEmpty()) {
      numberOfUnknowns.setError(getResources().getString(
          R.string.input_required_error));
      return;
    }
    if (!InputChecker.isInt(numberOfUnknowns.getText().toString())) {
      numberOfUnknowns.setError(getResources().getString(
          R.string.not_a_number_error));
      return;
    }
    Intent intent = new Intent(MatrixUnknownsActivity.this,
        MatrixInputActivity.class);
    intent.putExtra(getResources().getString(R.string.text_key_matrix_size),
        Integer.parseInt(numberOfUnknowns.getText().toString()));
    intent.putExtra(
        getResources().getString(R.string.text_key_subsection_name),
        subSectionName);
    if (subSectionName.equals(getResources().getString(
        R.string.title_activity_lu_factorization))) {
      intent.putExtra(getResources().getString(R.string.text_key_method_type),
          ResultsMatrixActivity.FACTORIZATION_TYPE);
    } else if (subSectionName.equals(getResources().getString(
        R.string.title_activity_gaussian_elimination))) {
      intent.putExtra(getResources().getString(R.string.text_key_method_type),
          ResultsMatrixActivity.ELIMINATION_TYPE);
    } else if (subSectionName.equals(getResources().getString(
        R.string.title_activity_iterative_methods))) {
      intent.putExtra(getResources().getString(R.string.text_key_method_type),
          ResultsActivity.ITERATIVE_METHODS);
    }
    MatrixUnknownsActivity.this.startActivity(intent);
  }
}