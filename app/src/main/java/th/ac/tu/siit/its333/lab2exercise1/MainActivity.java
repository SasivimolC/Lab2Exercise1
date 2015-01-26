package th.ac.tu.siit.its333.lab2exercise1;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends ActionBarActivity {

    // expr = the current string to be calculated
    StringBuffer expr;
int memory = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        expr = new StringBuffer();
        updateExprDisplay();
    }

    public void updateExprDisplay() {
        TextView tvExpr = (TextView)findViewById(R.id.tvExpr);
        tvExpr.setText(expr.toString());
    }

    public void recalculate() {
        TextView tvAns = (TextView)findViewById(R.id.tvAns);

        //Split expr into numbers and operators
        //e.g. 123+45/3 --> ["123", "+", "45", "/", "3"]
        //reference: http://stackoverflow.com/questions/2206378/how-to-split-a-string-but-also-keep-the-delimiters
        String e = expr.toString();
        String[] tokens = e.split("((?<=\\+)|(?=\\+))|((?<=\\-)|(?=\\-))|((?<=\\*)|(?=\\*))|((?<=/)|(?=/))");

        int result = 0;//Integer.parseInt(tokens[0]);
        String op = "";

        //if(a%2==0){
        //    result = Integer.parseInt(tokens[a]);
        //} else
        //Calculate the expression and display the output
        for(int a = 0; a < tokens.length ; a++){

                  if(tokens[a].compareTo("+")==0){
                      op = "+";
                  }
                  else if(tokens[a].compareTo("-")==0){
                      op = "-";
                  }
                  else if(tokens[a].compareTo("*")==0){
                      op = "*";
                  }
                  else if(tokens[a].compareTo("/")==0){
                      op = "/";
                  }
                  else
                  {
                      if (op.compareTo("") == 0)
                      {
                          result = Integer.parseInt(tokens[a]);
                      }
                      else if (op.compareTo("+") == 0)
                      {
                          result += Integer.parseInt(tokens[a]);
                      }
                      else if (op.compareTo("-") == 0)
                      {
                          result -= Integer.parseInt(tokens[a]);
                      }
                      else if (op.compareTo("*") == 0)
                      {
                          result *= Integer.parseInt(tokens[a]);
                      }
                      else if (op.compareTo("/") == 0)
                      {
                          result /= Integer.parseInt(tokens[a]);
                      }

                  }
           }
        tvAns.setText(Integer.toString(result));

    }

    public void digitClicked(View v) {
        //d = the label of the digit button
        String d = ((TextView)v).getText().toString();
        //append the clicked digit to expr
        expr.append(d);
        //update tvExpr
        updateExprDisplay();
        //calculate the result if possible and update tvAns
        recalculate();
    }

    public void operatorClicked(View v) {
        String o = ((TextView) v).getText().toString();
        char c = expr.charAt(expr.length()-1);
        //IF the last character in expr is not an operator and expr is not "",
        if(c != '+' && c != '-' && c != '*' && c != '/' && expr.length() > 0) {
            //THEN append the clicked operator and updateExprDisplay,
            //append the clicked digit to expr
            expr.append(o);
            //update tvExpr
            updateExprDisplay();
        }
        //ELSE do nothing
        else{
            return;
        }
    }

    public void equalClicked(View v) {
        TextView tvAns = (TextView)findViewById(R.id.tvAns);
        String i = tvAns.getText().toString();

        TextView tvExpr = (TextView)findViewById(R.id.tvExpr);
        tvExpr.setText(i);



    }

    public void ACClicked(View v) {
        //Clear expr and updateExprDisplay
        expr = new StringBuffer();

        TextView tvAns = (TextView)findViewById(R.id.tvAns);
        tvAns.setText("");

        updateExprDisplay();
        //Display a toast that the value is cleared
        Toast t = Toast.makeText(this.getApplicationContext(),
                "All cleared", Toast.LENGTH_SHORT);
        t.show();
    }

    public void BSClicked(View v) {
        //Remove the last character from expr, and updateExprDisplay
        if (expr.length() > 0) {
            expr.deleteCharAt(expr.length()-1);
            recalculate();
            updateExprDisplay();
        }
    }

    public void mpClicked(View v) {
        TextView tvAns = (TextView)findViewById(R.id.tvAns);
        String tv = ((TextView) v).getText().toString();
         memory += Integer.parseInt(tv);
        Toast t = Toast.makeText(this.getApplicationContext(),
                "The memory is" + memory, Toast.LENGTH_SHORT);
        t.show();
    }

    public void mnClicked(View v) {
        TextView tvAns = (TextView)findViewById(R.id.tvAns);
        String tv = ((TextView) v).getText().toString();
        memory -= Integer.parseInt(tv);
        Toast t = Toast.makeText(this.getApplicationContext(),
                "The memory is" + memory, Toast.LENGTH_SHORT);
        t.show();
    }

    public void mrClicked(View v) {
        expr.append(memory);
        Toast t = Toast.makeText(this.getApplicationContext(),
                "The memory is" + memory, Toast.LENGTH_SHORT);
        t.show();
    }

    public void mcClicked(View v) {

        memory =0;
        Toast t = Toast.makeText(this.getApplicationContext(),
                "The memory is" + memory, Toast.LENGTH_SHORT);
        t.show();
    }






    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
