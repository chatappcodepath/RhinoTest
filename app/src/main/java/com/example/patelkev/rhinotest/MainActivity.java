package com.example.patelkev.rhinotest;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import org.mozilla.javascript.Context;
import org.mozilla.javascript.Function;
import org.mozilla.javascript.Scriptable;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    TextView tvOutput;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvOutput = (TextView) findViewById(R.id.tvOutput);

        Context rhino = Context.enter();
        rhino.setOptimizationLevel(-1);

        Scriptable scope = rhino.initStandardObjects();

        // Note the forth argument is 1, which means the JavaScript source has
        // been compressed to only one line using something like YUI
        String javaScriptCode = "function sayHello(a) {return \"Hello \" + a;}";
        try {
            javaScriptCode = FileReader.contentsOfAssetFile(this, "eliza-min.js");
        } catch (IOException e) {
            e.printStackTrace();
        }
        rhino.evaluateString(scope, javaScriptCode, "JavaScript", 1, null);
        // Get the functionName defined in JavaScriptCode
        Object obj = scope.get("transformText", scope);

        if (obj instanceof Function) {
            Function jsFunction = (Function) obj;

            Object[] params = new Object[]{"Men are all alike."};
            // Call the function with params
            Object jsResult = jsFunction.call(rhino, scope, scope, params);
            // Parse the jsResult object to a String
            String result = Context.toString(jsResult);
            tvOutput.setText(result);
        }
    }
}
