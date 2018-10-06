package cz.firstapp.jackson;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.JsonObject;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import cz.firstapp.jackson.model.Configuartion;
import cz.firstapp.jackson.model.DataResponse;
import cz.firstapp.jackson.model.Initial_screen;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    Api api;
    Api api_JSON;
    ApiClient ApiClient;

    private Button b_getJSON;
    private Button b_decriptJSON;
    private Button b_version;

    private TextView tv_showJSON;
    private TextView tv_showDecriptJSON;

    String action = "action";
    String value = "downloadConfiguration";
    double actual_version = 0.0;
    String status = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        b_getJSON = (Button) findViewById(R.id.b_getJSON);
        b_decriptJSON = (Button) findViewById(R.id.b_decriptJSON);
        b_version = (Button) findViewById(R.id.b_version);

        tv_showJSON = (TextView) findViewById(R.id.tv_showJSON);
        tv_showDecriptJSON = (TextView) findViewById(R.id.tv_showDecriptJSON);

        api = ApiClient.getClient().create(Api.class);

        final ObjectMapper objectMapper = new ObjectMapper();


        final View.OnClickListener onClickListener_VerSion = new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                JSON String
                String jsonString = "{\"status\":\"OK\",\"actual_version\":0.01,\"configuartion\":{\"initial_screen\":[{\"color\":\"#0000ff\",\"text\":\"software\",\"icon\":\"data:image\\/png;base64,iVBORw0KGgoAAAANSUhEUgAAAHgAAABkCAYAAABNcPQyAAAAGXRFWHRTb2Z0d2FyZQBBZG9iZSBJbWFnZVJlYWR5ccllPAAABQhJREFUeNrsnU1PE0EYx7uzu30DalskHCxVIUIQMGIlGgmJ8S2cCAknY0jQz0DCZ+BbcPXgVTnKgZQDB48cCCaEA4i1QoUWqLs+Q6dkqAVb22V32\\/8\\/eTLb7WZ3ur955pln25kqpml6oMYVwy0AYAiAIQCGABgCYKhKaVacVFEU3NkqbpcwJm0bZL\\/JzFrTWM3uT9cMebhSaPGsFOb29naC3moPBoMPqIzqun7f5\\/O9pveOyDKidDfgBoLIyngjS6fTz8jaVVXt0TTtNpW3COQ9Ov7aBaeKkOXITgD46r3wL4DJZDLS29ub2Nvb66HX7eSB9+nQsNfrHfvPS+mii64LGwC+vCs9fb2+vt6zu7vb7ff7E4ZhMCqfMsbC5JFDFsZkDwDX3pWeAaVu9PnOzo4aCAQSBC\\/C4yF1pzfJ4hhFO7MrFWM40+T71tbWOjo7O0dSqdR1gtddYTxEmmQzxHMDm42NjW7qSnu2trY2h4eHv\\/JBCh16TIwN5MEuSi2oKz2XWpTGw83NzbdU7JPtiXwSDzpsEivnjTwelqYW1cTDg4MDPxUtUj4JwBY\\/pTkHkFKLKE8tCN651KJe8ZCHXXE9tZ4jUgCWwNJg5g6VvZQPPiymFvyNGvJDyCmAm3lQ4zTh2yQAhgAYAmAIgCEAhgAYAmAAhgAYAmAIgCEAhgAYAmAAhgAYAmAIgCEAhgAYAmAIgAEYAmAIgK9aiqLwlV0MYW5b5cWUrGZZtYySj4rrZCFPYQKYHTeJzyzki5kYDqhPtXU\\/JDt2LGBSnixNlrWplzBFHXKSF9tZn2rrfizq7ljAhqgg9yLFxhtlipXE+DIOdten2robjgWcz+dfZjIZbzab1Wn7yj1maWnpy\\/T09HfeyAjsCdXhRTqdtq0+1Sgej3+WPDhfc7i0aKU5Wwc2q6ur70ZGRviN+km2T58x73GJqEH2ixicIjuolU9DjqLJU\\/mgii\\/hEHDhZwyIunuRJl3Ufbh7CYdi3RkAQwAMwBAAQwAMATAEwBAAQwAMATAAQwAMATAEwBAAQwAMATAAQwAMATAEwBAAQwAMVSRLpq5ks9k3h4eHg4qi3PH5fF2BQKCfMRbC7W4QwMFg8D0VH8naPIVf6PMfoLPFxcWha6RYLDbg9XpDLS0tA9QAbmiaFgMKFwH2FGbG8X\\/9zIprnM4yGB8f3xWwP4n9atEWFhbifX19cQ6fz0yIRqOPVVUN+f3+fmByHmA+Y+q3sKMycV+VytPtmZmZb1R+EZ6vyQ1jdnY2MjU1NdjR0dFFns87gbscPvUAj\\/5RB7PZAVsyu7Dwl8AVtgTp+uK\\/hFUZfEmpS\\/DP9ieTydHW1tYQgY8T9Njy8vKHiYkJ3lj4pO8UXePYNUAUJSGcYlvUvXEA\\/+OcSqnXl2zL8PmxJyJM\\/CK9yuVyY2T+cDj8xMldf9MCruCaMmwmxgF5ESb461Zp0MdE3L8px\\/0Ku34AtgNwhQ1AK+P9ctd\\/1kjm5uYik5OTQ8W4z7t+GvHHrE75ALj+dS0X91kZ+Jo8JlhZWRkl6HzAF9d1PdTW1na3HikfAF89fHYJfE1qAGf75Xy\\/2pQPgJ3VAMqBZ2Xgq5XGfTrnAwnwDwB2Lnx2Qap3adyfn5\\/f9xRW2OGrBGVcD7hZnz9cEveLKR5\\/CpivlY+Ge22LiivxWb68E74ubHBZ4sGNGlfdKHgwAEMADAEwBMAQAEMADAFwM+mPAAMAx5a2bhowJdAAAAAASUVORK5CYII=\"},{\"color\":\"#0000ff\",\"text\":\"citrix\",\"icon\":\"data:image\\/png;base64,iVBORw0KGgoAAAANSUhEUgAAAHgAAABkCAYAAABNcPQyAAAAGXRFWHRTb2Z0d2FyZQBBZG9iZSBJbWFnZVJlYWR5ccllPAAADx1JREFUeNrsXQlQlNcd\\/\\/biXBAwQcRbPCaIiglG1MzgVLRJi1rTBqNmqi22mGYaR6dqEyeZMTHawyRqTaZa05pRk8ZGE6EqEG+DNgjG2zHjzSEbkWVh77O\\/B++Tx5dlWWB32XXef+bN7r73vve9\\/f\\/e\\/3rH98lcLpfA6dElOWcBB5gTB5gTB5gTB5gTB5gTB5gTS8rOXiCTyTjXgpik8xpKP2gEMclo4tRFrJhPJ5KDyfOfBLsbMZBquU6nexE\\/h4WFhU1FnowkjlHXyWQy3TUYDHcPHjxYsHjx4uvIMtNk7xTQBKDOJClZLJY5DofjlouT3+jq1avvzpkz50mwuw9SlCff6Qd4dQdgq9W6mLM\\/MKTX6y\\/PmjVrAtg+AEndHshSvGSdXWwQnSxIbb5cLv87V6aBI61W+01CQsIifNUh1VGV7fLkZHUpTIK9TQDQf+IsDyzFx8dPOH369Bx8jUGKRVL4JQ6OiorKB8BxnOWBp\\/T09F\\/hQ0XVdIRfvGiAm91emdlsroYHWMWh6L60usuPiIjot3HjxlFLliw5h5+RSEYaRvkOYIVC8SN3+RqN5lBSUtJKGrPZuxq7cRKEwsLCSTk5Of90V5aRkZGKj4tI4RRDq9\\/iYJZqa2uvUkAtSA0knONQdY1mzJjxBRwmtwBHR0fHUvOq7MjMKv3QNwKwDakJHTRwqLyLSro5c+hbJ8sLgF1cNQcH8dUkDjAnDjAnDjAnDjAnDjAnDjCnNqQMtQ6T3SNC65YgMgfrdPEDVqEvwQBWZrVaXzMYDFuuXbu2EFm9hZZlM5WM7wQMfYCdTueesLCwtVFRUYtGjBjxj9OnT5OF78eElnVRJYcyhAF2OBzPQUhns3mZmZlvjxkzhkhxL8GLdVEOcHBLb6K7\\/Li4OLJcRlIYV9MhDPC9e\\/cK4EfdZ\\/Oqqqr2nThxokl0tDiUQe5FUwlkk0v0kAcNGtSwdu3aJ\\/Ly8tYqFIpBt27dqhg\\/fvxuoWVTAdnRYGHa8Eiix+2ubkfeuPQaUt\\/bdjz0zcX8X9\\/ztSu7KttjxPnz5\\/+Wnp7+Eb4SyapFNaM3wNbU1IxUqVTj4CX31uv1TWjnam5uLtnsTdaVrfSTMEFNbS7ZqiIMGDDAvnLlSsXw4cNNuE4OD9utRsKgkH3yySdaqHTH8uXLY7Raray2tlZps9lkpMxkMjlWrFihra+vJztQHib0n2gGOdruffHixTBoEqXZbFaQtvbv32+urKzshz5br1+\\/7tJoNErS1vz58+9TjUL6bH\\/w4IEa9WJu3LihJP0bMmRI7JUrV7T5+fl1p06diofj6MzIyHgguDm94C2f6SAXr\\/nBaOv0vuj29u6eO3duE4rHIg1FiuqoLTA2C0wucdeWTqf7eu\\/eveS0RH9ibu12+wnYYhMcLj0+LYcPH355+\\/bt0\\/Hd6k0qKCiYW1RU9PN2ys3oRzUALvzss89m4H59Re+8qakpEd1pZOsXFhbOQ9lAaJK5pK9sGf7TZfr\\/+545cyYNeQ1iGarayb2WLVs2afPmzRPr6upex+BeREO+H0QD3vLZE549ZoMB2KqIiIhjSqVymrvy2NjYybNnz\\/70u+++W0XCIQAbD2GPkMvl0fgMw\\/VqMD8G31XeJKPRGIv6se2Uh6MfyfHx8TnQHAV79uz5Ke75OOkGACA7GNvcByASLRIDaTxusViOsWX4T6nHjx8nAyAOXv4e5PUSy4gyOXny5Lr33ntPP3jw4BRoAzn+VzLyo6lWUviazz0CMKTlFTB0jTd1IyMjk8goxTVt9nc1NjaaoRLDvb0nVGE41KVZMtqbJUpad9asWRsGDhxItgXHlZeXh4s2XiSA6qBgKFCXaIUmthz+wSKo7T\\/iniPY\\/Orq6gNZWVkFRB3HxMQQFW5CnzSCHw\\/rBRxgjP5+sLeb3YD+PSTsAph3j2FkNezs2wQLSECb0Z2YmJgAVQUtV1eAtA+MOiptE\\/mlsJv7kQ5cuHDhLqQmQdKXuzNnzvzJjh07FpF7MTY7evXq1eQsUOSdO3c8xdiu4uJiAzz6eZJBOSglJeWXkkFR279\\/\\/zeonTVs27btKEzCsUuXLp2lfobVL9FAoG0wVNJG6XVwOj5KSkoaj2vGII0qKyt7k9gs2OBXaHsjAEaZ5JqttGw48bfS0tKekLb7wgsvEPWfTtok9SBVb7HlcHqII0eATIMpWMOWlZSUvI780XDK0vHTzJZhQPyW9pX4B2FkTMAR+9jT2aJVq1bNpn0hUh1PNYCS2tAYqqLlIW+DIYnPsb8bGhrOpKamfgCvlqhBPdL3Tz\\/99OatW7dmPf\\/880VktCPpoNIdktktmzgPQrxJSIRKeq+JEyc+Tsub24WNbZBMoDQfxezTp48M0jWFLbt8+fIdb1UmGOlQq9W\\/gUNd4abMBpv81jvvvHObSik5V6Qn1xATIbScLxJDPZ9LsLIHAO7H\\/v7222\\/\\/S\\/8Y+YNaygDX4sWLy+kUJGGyBde5PMSRVoQNlh\\/YH5DQuo3XALtnlajSvlDtm5GfArPRh1Hdt5cuXUr2eDuio6O9ZboN1\\/0bdvcpNhOgP5gyZco+UTUT94GAzo6zR3omiy7\\/iSBYSNRBNZGNMkQveNi5zzDSk7TJ2omPYxMSEiYx4DphK2ugTl+lgJhht73avA+PeCg8\\/zek+eHh4X0QLr0otJ7UD+isW8ABBnCV7O9x48bNpAA02yQZJa1WO5rat3B\\/hA\\/uCODWIcyZ8f7771dS1amDZjB7M1kDyT2Mj1h3xRkZGcvz8\\/P7UTsbRQf1owkw4tci9nevXr3GwflZmp2d3Rzsw6npgxBoGezleYz8eWI8GohHQgCk3qdOnfoFYzKMycnJDi\\/+06cAbZCnOuvXr3+Xmpzm1a9ALY4EHOBbt279VZpHQoqioqISo9F4aN26dVdgE9eTkT927Ni1lCFxcIh8LsUIo77WaDQljCQq4Ji9tmvXrkyqOSJramo83heh3SKo+jmS\\/7j7xIkTq9k8OGGpGLDz6aRGwNawAw7wyJEja6B+X3ZjD2Pg9IzEZ7yYR2xjdXU1ATkKUuKXyXiozzfhkbc5QzV37tzNubm5ZIYp\\/uLFi2o3kt7ssX\\/44YdDANwHEs\\/cNHTo0D9nZWV9gdheI7nXCkQGiRTg6ECo6p6wwS44NtsgOW94U7++vv4udYjb2DeAHyGxgwo3QLB5MjA\\/ki2Pi4sbVVVVZYX2WCFpS7Vz587\\/oFwNEGVUmh\\/SzZs369C2AE\\/\\/oPSeuG4pVfFmRAi\\/l5Yjht7KqmrBz4+a6ikv2pGUlPSXI0eO\\/Bhx8CGpZ0k8aajP45s2bZo\\/evRowhAbpOw+ANITaSMJIUmjwBxyI143UhNbByrfynivLgwSHVsHnm8Nyc\\/JyTkCu3+AvRYDqPfJkyd\\/XVFRQeaLv0fSQotoyWLH7du3dWVlZfMwEKLZa65du7ZtwYIF31Cvv3HChAmF6GcBWycqKmoY\\/neO6HD5G4MeWQ+m66i2qVOnHsPP8jVr1oyAN50GFR0LwI3FxcU3tmzZUktDleYJkNWrV+dCmtWwiTHwdlWVlZVW6umSMMYOKbyh0+mGg3nxGBxqgKE4d+6ckZY3P6zkyy+\\/3HHlypUDkKx4vV4fAeYLtH0TnL2XFi5c+Ni9e\\/dioFqVIFlmZmYcwhwHvj9JgSD2U5mYmCjv379\\/0YYNG7ajT2EGgyEcIMqPHj1KQjq7ODlD2gaguXl5eQOgKWJJu\\/itGDZsWCStJ\\/hbgoNhPVhBw6EImsRB56CSID4AzMnUE+vYKUDN8TNtK4IJrVxMGzZmUEfQtsStt1bajpyWqRjGOy5cuPAkHMFXMWiiLl26tG\\/y5MkH6cAR\\/59CaF20F2enrLRPMqbfKqaeOHjN7cXGvlgP7vEdHWTKjjALfDBTRsmZGSpx0f3hfALqWdpjAm3LgDpGD4yyodzuodzChjCQ8smIjb8Sf0+aNGlqeXn5W3CYdjJTj22ePifpk\\/jEA4skNJIJAThHHTRbdigj7F7W61adzpRD9f5OWp6amroAH3uoFBpR3dqJ\\/8hOsfr94a786EoHBIDvuJnx0gitq0GKYO4\\/B7gDOnv27HrY3msP3X94wvDu1wttnyQUtMRPBHRACHXqhZY1YzIBo6Y8E523RkGy24MDHGJEQzriAD6g4Y\\/onROv3EzXdDnAIQ6ykwnXQoq4DX7EiQPMAebEAebEAebEAebEAeYULADzl2IFCfl0oiMlJSV7+vTpn5eUlJAJgUiZTObgLO4aGQyGP3goa6RfO1xu7BLADofjiLvH+qvV6ieKi4uP63S6\\/8nlcrLYTbbJ8GdYdZJUKtVAktorLysrE5+sTwTI40b6Lu3oMJlM5GzvGg5F4Im89CQyMpIcFiCLHeTdSfcFZkXLJ+9Nwg3+BCm+w9kdeKqoqPhYaN1mZOpIgrvqZDnv37\\/\\/Cmd3YKmysnLvM88887nQurHP3JEN7irArr59+xZpNJqlTqdTx1kfGHDz8vI2UXDJ7s1GwYtDed19+6hq9+7dTzU2Npby10f6h+x2u660tJT4O+TweBrSYKFl07zCGzy7\\/HJKJt4lnnj0xo0b05599tmf2Ww2RTC+OzgtLW2Ju\\/z6+voySMc34vnjYPL6a2trq6ZNm\\/YVY3PF\\/dZGoZ2tQlI8uwvwwxdECy17kclOfXH\\/b1DNkqGfN9zl0z3G\\/xLo+WQh+PZYsQ6VkX53edLIPp\\/ooDseTHTPsrjbMJRmssS9yw+E4Nu1IT4grXNv\\/vbHTBYFWnzKmxBiADef6PfmNIYvie+L5sQB5hQgFe0H9SUTWt+y2a0TBB09SJa2H9FNlSkeVbXTc1JB4V12Jw7u9PWdSU6n81CoxKsOh6Nh165ds4SWh6M1v369K\\/z0NZ5Bq6IR4E+DNE0NGVsnl\\/fKzs4m07dxdCJCxW3wo2bvlMpeQuv5Yw6wJ4LKC9UdIaLfIOcAP9rk4gB7IIvFcjPUECVz2oKXOy0Cpk58MRftrxCptLR0VHJy8ksmkykMKjuotQ3ZJ5WZmUnWasksHlnKqwNvLJ3lpy+8aJ8C7GcisSl5sg05lxsWAiZFPIVIHo5i6gkpDrqHsHTkawkti9smITQWMMTnjHRpYcAvnn0ImLZQXcAIjvics4ADzCmEqdMqmr+LmUswJw4wJw4wJw4wJw4wB5iz4NGm\\/wswAINp9H+iGUL+AAAAAElFTkSuQmCC\"},{\"color\":\"#0000ff\",\"text\":\"equipment\",\"icon\":\"data:image\\/png;base64,iVBORw0KGgoAAAANSUhEUgAAAHgAAABkCAYAAABNcPQyAAAAGXRFWHRTb2Z0d2FyZQBBZG9iZSBJbWFnZVJlYWR5ccllPAAADJVJREFUeNrsXW1sU9cZvravHePEDmnSwkJGgAqWQNtR2oaxDVGtZUgwTR6auk\\/tx0QromSkSPuzaf+mTpP4tVURUgtax09EWdiPrAjGBNqokvKRTVOyTZSSEueLJHa+bMeJffe+znvJibmx77Xvh699XumVnROfe8+9zznv13nPOQ5JkgROpUtO\\/go4wJw4wJw4wJw4wJw4wJw4wJw4wBxgThxgTrYgUa8LORyOYnouhw6dV2K4aEhraFm0W4Oz0dTUVG1FRUXL\\/Py8e3Fx0ZVKpfLqddFoNNLU1NQHXxeBF\\/BTMihob\\/TAcOjV7nwbqtf9k8nky06n8wq0Y70e14vFYh\\/7fL6fwNd54Ahw3AiQtb43rU2whQ52LJML2L0WA7jn9QIXad26dfsGBwd\\/Bl\\/xmtXA3mz3BxaBi+59isUMKnbAubm5b4LIfAVErggi15nl91v1bkNtbe1eAFlwu92LHo8n4XK5Ukq\\/g9\\/c2L179134moB2LJBoT0lFMBdblCIadanf7z8uiuLP9RyVRtLS0tJQKBR6b8uWLefgzzkS7Ql4vpSVIjpdQQ8uxMhiGUbq92CkhiWbEhh4H587d+4gPFoDsB\\/Yped704xLMQEMo+C4VAIEBt90d3f3G\\/B4W0mHu8oeYAD3oFRChCB3dHR8HR5xG3AA7QkrAC4KHQwuyVNer\\/fTbPoWRF9PtutUVlbu1VuvgroILSwshKBdii8JjK4AtLs5W5urqqreJDdrAp41VpY6GEbvL9YaBQ8ePPh9e3v7AfjpC8C7gJuAv5TJAMbneo9CvDdc+0Xg54Gble6L+nZ2dvajta5x48aN41S3TklUl4WIBqPqvtLL6erq+hH8ZDfwTuAtwM8wfukqHhsb+6me4IJU6aeO9Tzp0lql+1J7nh4fH\\/+t0nUmJiY+pM7ZiL502YloGL0vg6j7RMG3fBdcjj+gqwEcZqJJqTXu7+rt7X1h8+bNP4BrZvWZc1E8Hp\\/ZsWMHApPEyCXwJPBslnvjvbwgzj8Cf3k\\/+z9oxww836t0nVG4xrSZIroYAh0HlQpPnTp1Ed8PEyqM5QgcpFpaWv4Nn+8AVwG7C4zUpahzoU8bzebP4v8AqBiolPPw5yqAnU5nACTRzmAwiLHtCuwMuXxj3YP9VopoeCkXlMQj\\/GsP6dxn1rJA12iHk8BdB+wrgNfRdRxq7z08PFyXRZejmP4isMdMEW35CIYe\\/VpmWTgc7hWWp+mWcDZHS4+n36YoXGgq1dfXT4J66AORvJstB0t6k7A8hemhTpMwq02WBsfB8n1WyTUaGRkZYEBaFGxCpEJuZ5YHAoG99K5Fs9WipQBDT39dqRyMpX4awYs0im1DoHLuZJa53e5NbW1taHG7EGCHidkRlgIMFuazSsGF1tbWEI3gBRsCfEup\\/NixYy0EcAV9lj7AYGE+oX+j0egAo38XTbU4dSCfz3cL2hzJLN+4cWMzvW+PmWLaMoDhJdSApNqTWR6JRGSAk3bSv6yRB3QtsxwMrZ1kaLmJSxtgTLFRKh8cHBywq\\/5lQL6tAHALASyWxQgGUgT4wIEDPUyQwZYAQ+e9rVR+\\/fr1r5D+9WDkraQBhgd8woKem5vrld8RGVhJOwLs9XqvKJU3NjbKerjCrFFsGcBgYH0js2xyclIevThyE8WQ05SvHga+m1m+fv16GWB3SQOMEwxK5eFweMju+pcR03\\/NLPP7\\/S1mG1pWjWDFCYYzZ870CisRLFsDDHRfQWqlJx4YPewsSYDhwV5SCnB0dnbOMAGOpJ3RBX\\/+qlJ5U1NTswywGWLaKoCf8H9nZmZ6WP\\/XbgGOTKqurr6nFPCoq6trFlYmHkoPYJpg2KpgQYeElQiW3cVzeuJBKeBRWVnZbKY\\/bDrAoIdeUirv6+vrKSH9u2bAA5P02tvbq+ndG+4Lmz4fvLCwcGd6ejo4NjYWiMViFXJqTTAY\\/Bfp3mipAPzw4cP3RVH8z8TEhB+e2y0\\/q8fjkTtxikazYe6gGTlZDoYFRv9UksMvSxGJAMYUmYRQZOty830tOZ51nj4lpjzrmuRiyclKi59Hjx69Ar22DnxCJ\\/i+TqaRDijDzpVm6iASssvlktbKQ7apmH7iWUlVpdhnDYVCD\\/bt2\\/eZsDLJskTfU4U2QNecLBBF3wcw70qc8qLZ2dm\\/XLhwAdN1vyDQ2qZC8NJVRGMCO\\/TK3wicCiJMtb18+fKbhw8fxnRiTBmeleMCli0Ax7VFHFzdPI3AoUOH3u\\/o6MBkvRphOcszrzQfp46NeotDoy\\/IJ0+efIMMNH++9pJuAIOI\\/i6HRV+i2SfUwV4hz8kJvk9WEROuXmSiXi5LAVaa\\/+RUGIFFPcD4x4KlAIOR9SGHRF\\/q7e29IhQ4P64bwENDQ++BQ\\/9PDos+NDIy8sdgMNhP4EaFPDNMdQN427ZtEzdv3jycSCT+zuEpjEZHRz+or6\\/\\/HYE6R5zX\\/LjesWjsMD4QLfsbGhpegxHtgs+31V4DJ\\/0nJyev+Hy+TYFA4KDWNhRaHwMM4XA4PVFfW1t7tJD6NTU1r6Oro7bdw8PDFzFs2d3dfaW1tfVzAneeAh34mcon0GHE8lEnmfW4Ir5B7dYKYFD0tLW1vQp1vgy8c2BgoENLiC8SiaC+2kP1m69du\\/YdLfVB8gzR\\/XFl4K6rV68exS0kzKgP4H4grGwVsR14M\\/DTwvISVmcheBm5Pji9TAMA\\/pOah7x48eKP6eXg3hfprRri8fif1b7gzs7Ob1P97fRyqqPR6Dm19aFDvUMvGX1PjANXQ6d7N4\\/9PHbK9aHT\\/VpDXbntTwlZ1i9pxcVIP1hOXv9EzY+PHj3aT3pmjsTSNIisXrU3g9EzxNbHrRJEUfyv2vrj4+ND1OY43hvrV1RUjKqtTysyVtX3eDz\\/0FBXYgwq3XLSDA904AYran7X1dXVLKzkZKX3ecQptTw6FeZzLZFdoNXAWJUylGf9xy4NAPyimkrnz59n10PrmmxoRiTrUzU\\/AqNEjto4hZXAejifmIsVli+B9LiDkjjNOUGQkU2asB3AIOZuaTHGSfek2wVGyn3BJkQgyRJAteTBjdaoXkpr3WIZwZKaMOaGDRs2MSA7KBZrt8wOidRLGiRQMXtyVaDlOvLI1z0XzZTJBtDDd3L9xuv1siLaSfXCdkAVxSwLMKt5ctWNxWKzzMhP2hJg6NH3NbbJkYd4t4wYMav5EA+y3u0N8FrrZVkCizMgZGRg2nB14aoRrGYXeloPbW+AoYfnHMF+v7+ZaZOt5qmhA88wbpqkFuB4PC5PByaNsKBNAxjX6Wiwolk3SVBa\\/lFsxOwrIhVgQS8a4eKZNVJwa+C\\/aQDZIdiT2BFco6FjJAWDVlOaJgqV1umwRJuUsKPYbiSxAK+1yQxLzIL3JdsDLKiLSjkydbAaF8tqoljyKiNLTRQL6oVKBmA1lnQG0Fo6RjGNYNU6OBgMGhaDNh3g+fn5nD4tTTikw5XFeIqYFh2stIsBS8yOQjLAKVsDXFtbO6W04p0lZsLBxbSt6EcwM9HwGOBcIhos6CFh9Y5+kq0BpriFGn1quwmHfCYamB0NkkaNXrMBRrCy+rQ6TThY5WJpmmhgJvkN3bLCVIBBL01l+38gEGgQMvavgDrb1Fz79OnT8q7qorxNoNq6TOfKVA81Gu\\/92HbIde\\/+\\/v4how0sWW6admZDPB4\\/lCs\\/6dKlSz8UlvOiNty7d28XuEmfqT0G58SJE3ggBr7Y9dFotF3rSWUA1LeE5ZywutHR0a+pPUMRE\\/7o3hiarAb9+qtc9xKWEwSfE+hMCqPwMvtYHTxcI2dvxRPDMF0Hz\\/DVZMKmUjPQifpFUfR7PJ5dmk1gqA8dZQDUQtLr9X5VS12cMgR+6Ha7q4Cfy2VB4+mqwnL+1hjwlBaANeFi9rlJmKNlxFm\\/diJctUCJ7ZjvjIl9M0YBbLqvCQCX\\/SK1RCIxIxgcg7YM4Fwx6XIgxoIuPYDVptGWMhmZJmu5Dp6ent4O7tD\\/yhVcNMbAAMRVGJjcPi4sn4uo5eCv4h7BOPkPbkJfuQKMi8yYAEfCyCiWJQDjw4Er88tyHb106CaCG6NRbLjRY8XhlK5IJPI26ONIuWxwhoEYOg8ZVz9upyiZ02i8rDw\\/2H327NnGI0eOvAVO\\/341E+R2JEzIGxsb66HziJcouIGRLFygljD6\\/GArAZZjzrgGtoo+XYJ987Gy4kLWMoKbPo+YwJbyGcFaSLT4oRcZfeQpA4Dl01RNy\\/cWi+ThbXWMrJ1IN4BtesRRyRPf6Y4DzIkDzIkDzIkDzIkDzIkDzIkDXE70fwEGAHkIZWfZPrykAAAAAElFTkSuQmCC\"}]}}";

                DataResponse dataResponse = null;

                try {
                    dataResponse = objectMapper.readValue(jsonString, DataResponse.class);
                    tv_showJSON.setText(dataResponse.getStatus());

                } catch (IOException e) {
                    e.printStackTrace();
                }




               /* api.loginAccount(usedDataForPOST()).enqueue(new Callback<JsonObject>() {
                    @Override
                    public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {

                        String jsonStr = response.body().toString();
                        String jsonString = "{\"status\":\"OK\",\"actual_version\":0.01,\"configuartion\":{\"initial_screen\":[{\"color\":\"#0000ff\",\"text\":\"software\",\"icon\":\"data:image\\/png;base64,iVBORw0KGgoAAAANSUhEUgAAAHgAAABkCAYAAABNcPQyAAAAGXRFWHRTb2Z0d2FyZQBBZG9iZSBJbWFnZVJlYWR5ccllPAAABQhJREFUeNrsnU1PE0EYx7uzu30DalskHCxVIUIQMGIlGgmJ8S2cCAknY0jQz0DCZ+BbcPXgVTnKgZQDB48cCCaEA4i1QoUWqLs+Q6dkqAVb22V32\\/8\\/eTLb7WZ3ur955pln25kqpml6oMYVwy0AYAiAIQCGABgCYKhKaVacVFEU3NkqbpcwJm0bZL\\/JzFrTWM3uT9cMebhSaPGsFOb29naC3moPBoMPqIzqun7f5\\/O9pveOyDKidDfgBoLIyngjS6fTz8jaVVXt0TTtNpW3COQ9Ov7aBaeKkOXITgD46r3wL4DJZDLS29ub2Nvb66HX7eSB9+nQsNfrHfvPS+mii64LGwC+vCs9fb2+vt6zu7vb7ff7E4ZhMCqfMsbC5JFDFsZkDwDX3pWeAaVu9PnOzo4aCAQSBC\\/C4yF1pzfJ4hhFO7MrFWM40+T71tbWOjo7O0dSqdR1gtddYTxEmmQzxHMDm42NjW7qSnu2trY2h4eHv\\/JBCh16TIwN5MEuSi2oKz2XWpTGw83NzbdU7JPtiXwSDzpsEivnjTwelqYW1cTDg4MDPxUtUj4JwBY\\/pTkHkFKLKE8tCN651KJe8ZCHXXE9tZ4jUgCWwNJg5g6VvZQPPiymFvyNGvJDyCmAm3lQ4zTh2yQAhgAYAmAIgCEAhgAYAmAAhgAYAmAIgCEAhgAYAmAAhgAYAmAIgCEAhgAYAmAIgAEYAmAIgK9aiqLwlV0MYW5b5cWUrGZZtYySj4rrZCFPYQKYHTeJzyzki5kYDqhPtXU\\/JDt2LGBSnixNlrWplzBFHXKSF9tZn2rrfizq7ljAhqgg9yLFxhtlipXE+DIOdten2robjgWcz+dfZjIZbzab1Wn7yj1maWnpy\\/T09HfeyAjsCdXhRTqdtq0+1Sgej3+WPDhfc7i0aKU5Wwc2q6ur70ZGRviN+km2T58x73GJqEH2ixicIjuolU9DjqLJU\\/mgii\\/hEHDhZwyIunuRJl3Ufbh7CYdi3RkAQwAMwBAAQwAMATAEwBAAQwAMATAAQwAMATAEwBAAQwAMATAAQwAMATAEwBAAQwAMVSRLpq5ks9k3h4eHg4qi3PH5fF2BQKCfMRbC7W4QwMFg8D0VH8naPIVf6PMfoLPFxcWha6RYLDbg9XpDLS0tA9QAbmiaFgMKFwH2FGbG8X\\/9zIprnM4yGB8f3xWwP4n9atEWFhbifX19cQ6fz0yIRqOPVVUN+f3+fmByHmA+Y+q3sKMycV+VytPtmZmZb1R+EZ6vyQ1jdnY2MjU1NdjR0dFFns87gbscPvUAj\\/5RB7PZAVsyu7Dwl8AVtgTp+uK\\/hFUZfEmpS\\/DP9ieTydHW1tYQgY8T9Njy8vKHiYkJ3lj4pO8UXePYNUAUJSGcYlvUvXEA\\/+OcSqnXl2zL8PmxJyJM\\/CK9yuVyY2T+cDj8xMldf9MCruCaMmwmxgF5ESb461Zp0MdE3L8px\\/0Ku34AtgNwhQ1AK+P9ctd\\/1kjm5uYik5OTQ8W4z7t+GvHHrE75ALj+dS0X91kZ+Jo8JlhZWRkl6HzAF9d1PdTW1na3HikfAF89fHYJfE1qAGf75Xy\\/2pQPgJ3VAMqBZ2Xgq5XGfTrnAwnwDwB2Lnx2Qap3adyfn5\\/f9xRW2OGrBGVcD7hZnz9cEveLKR5\\/CpivlY+Ge22LiivxWb68E74ubHBZ4sGNGlfdKHgwAEMADAEwBMAQAEMADAFwM+mPAAMAx5a2bhowJdAAAAAASUVORK5CYII=\"},{\"color\":\"#0000ff\",\"text\":\"citrix\",\"icon\":\"data:image\\/png;base64,iVBORw0KGgoAAAANSUhEUgAAAHgAAABkCAYAAABNcPQyAAAAGXRFWHRTb2Z0d2FyZQBBZG9iZSBJbWFnZVJlYWR5ccllPAAADx1JREFUeNrsXQlQlNcd\\/\\/biXBAwQcRbPCaIiglG1MzgVLRJi1rTBqNmqi22mGYaR6dqEyeZMTHawyRqTaZa05pRk8ZGE6EqEG+DNgjG2zHjzSEbkWVh77O\\/B++Tx5dlWWB32XXef+bN7r73vve9\\/f\\/e\\/3rH98lcLpfA6dElOWcBB5gTB5gTB5gTB5gTB5gTB5gTS8rOXiCTyTjXgpik8xpKP2gEMclo4tRFrJhPJ5KDyfOfBLsbMZBquU6nexE\\/h4WFhU1FnowkjlHXyWQy3TUYDHcPHjxYsHjx4uvIMtNk7xTQBKDOJClZLJY5DofjlouT3+jq1avvzpkz50mwuw9SlCff6Qd4dQdgq9W6mLM\\/MKTX6y\\/PmjVrAtg+AEndHshSvGSdXWwQnSxIbb5cLv87V6aBI61W+01CQsIifNUh1VGV7fLkZHUpTIK9TQDQf+IsDyzFx8dPOH369Bx8jUGKRVL4JQ6OiorKB8BxnOWBp\\/T09F\\/hQ0XVdIRfvGiAm91emdlsroYHWMWh6L60usuPiIjot3HjxlFLliw5h5+RSEYaRvkOYIVC8SN3+RqN5lBSUtJKGrPZuxq7cRKEwsLCSTk5Of90V5aRkZGKj4tI4RRDq9\\/iYJZqa2uvUkAtSA0knONQdY1mzJjxBRwmtwBHR0fHUvOq7MjMKv3QNwKwDakJHTRwqLyLSro5c+hbJ8sLgF1cNQcH8dUkDjAnDjAnDjAnDjAnDjAnDjCnNqQMtQ6T3SNC65YgMgfrdPEDVqEvwQBWZrVaXzMYDFuuXbu2EFm9hZZlM5WM7wQMfYCdTueesLCwtVFRUYtGjBjxj9OnT5OF78eElnVRJYcyhAF2OBzPQUhns3mZmZlvjxkzhkhxL8GLdVEOcHBLb6K7\\/Li4OLJcRlIYV9MhDPC9e\\/cK4EfdZ\\/Oqqqr2nThxokl0tDiUQe5FUwlkk0v0kAcNGtSwdu3aJ\\/Ly8tYqFIpBt27dqhg\\/fvxuoWVTAdnRYGHa8Eiix+2ubkfeuPQaUt\\/bdjz0zcX8X9\\/ztSu7KttjxPnz5\\/+Wnp7+Eb4SyapFNaM3wNbU1IxUqVTj4CX31uv1TWjnam5uLtnsTdaVrfSTMEFNbS7ZqiIMGDDAvnLlSsXw4cNNuE4OD9utRsKgkH3yySdaqHTH8uXLY7Raray2tlZps9lkpMxkMjlWrFihra+vJztQHib0n2gGOdruffHixTBoEqXZbFaQtvbv32+urKzshz5br1+\\/7tJoNErS1vz58+9TjUL6bH\\/w4IEa9WJu3LihJP0bMmRI7JUrV7T5+fl1p06diofj6MzIyHgguDm94C2f6SAXr\\/nBaOv0vuj29u6eO3duE4rHIg1FiuqoLTA2C0wucdeWTqf7eu\\/eveS0RH9ibu12+wnYYhMcLj0+LYcPH355+\\/bt0\\/Hd6k0qKCiYW1RU9PN2ys3oRzUALvzss89m4H59Re+8qakpEd1pZOsXFhbOQ9lAaJK5pK9sGf7TZfr\\/+545cyYNeQ1iGarayb2WLVs2afPmzRPr6upex+BeREO+H0QD3vLZE549ZoMB2KqIiIhjSqVymrvy2NjYybNnz\\/70u+++W0XCIQAbD2GPkMvl0fgMw\\/VqMD8G31XeJKPRGIv6se2Uh6MfyfHx8TnQHAV79uz5Ke75OOkGACA7GNvcByASLRIDaTxusViOsWX4T6nHjx8nAyAOXv4e5PUSy4gyOXny5Lr33ntPP3jw4BRoAzn+VzLyo6lWUviazz0CMKTlFTB0jTd1IyMjk8goxTVt9nc1NjaaoRLDvb0nVGE41KVZMtqbJUpad9asWRsGDhxItgXHlZeXh4s2XiSA6qBgKFCXaIUmthz+wSKo7T\\/iniPY\\/Orq6gNZWVkFRB3HxMQQFW5CnzSCHw\\/rBRxgjP5+sLeb3YD+PSTsAph3j2FkNezs2wQLSECb0Z2YmJgAVQUtV1eAtA+MOiptE\\/mlsJv7kQ5cuHDhLqQmQdKXuzNnzvzJjh07FpF7MTY7evXq1eQsUOSdO3c8xdiu4uJiAzz6eZJBOSglJeWXkkFR279\\/\\/zeonTVs27btKEzCsUuXLp2lfobVL9FAoG0wVNJG6XVwOj5KSkoaj2vGII0qKyt7k9gs2OBXaHsjAEaZ5JqttGw48bfS0tKekLb7wgsvEPWfTtok9SBVb7HlcHqII0eATIMpWMOWlZSUvI780XDK0vHTzJZhQPyW9pX4B2FkTMAR+9jT2aJVq1bNpn0hUh1PNYCS2tAYqqLlIW+DIYnPsb8bGhrOpKamfgCvlqhBPdL3Tz\\/99OatW7dmPf\\/880VktCPpoNIdktktmzgPQrxJSIRKeq+JEyc+Tsub24WNbZBMoDQfxezTp48M0jWFLbt8+fIdb1UmGOlQq9W\\/gUNd4abMBpv81jvvvHObSik5V6Qn1xATIbScLxJDPZ9LsLIHAO7H\\/v7222\\/\\/S\\/8Y+YNaygDX4sWLy+kUJGGyBde5PMSRVoQNlh\\/YH5DQuo3XALtnlajSvlDtm5GfArPRh1Hdt5cuXUr2eDuio6O9ZboN1\\/0bdvcpNhOgP5gyZco+UTUT94GAzo6zR3omiy7\\/iSBYSNRBNZGNMkQveNi5zzDSk7TJ2omPYxMSEiYx4DphK2ugTl+lgJhht73avA+PeCg8\\/zek+eHh4X0QLr0otJ7UD+isW8ABBnCV7O9x48bNpAA02yQZJa1WO5rat3B\\/hA\\/uCODWIcyZ8f7771dS1amDZjB7M1kDyT2Mj1h3xRkZGcvz8\\/P7UTsbRQf1owkw4tci9nevXr3GwflZmp2d3Rzsw6npgxBoGezleYz8eWI8GohHQgCk3qdOnfoFYzKMycnJDi\\/+06cAbZCnOuvXr3+Xmpzm1a9ALY4EHOBbt279VZpHQoqioqISo9F4aN26dVdgE9eTkT927Ni1lCFxcIh8LsUIo77WaDQljCQq4Ji9tmvXrkyqOSJramo83heh3SKo+jmS\\/7j7xIkTq9k8OGGpGLDz6aRGwNawAw7wyJEja6B+X3ZjD2Pg9IzEZ7yYR2xjdXU1ATkKUuKXyXiozzfhkbc5QzV37tzNubm5ZIYp\\/uLFi2o3kt7ssX\\/44YdDANwHEs\\/cNHTo0D9nZWV9gdheI7nXCkQGiRTg6ECo6p6wwS44NtsgOW94U7++vv4udYjb2DeAHyGxgwo3QLB5MjA\\/ki2Pi4sbVVVVZYX2WCFpS7Vz587\\/oFwNEGVUmh\\/SzZs369C2AE\\/\\/oPSeuG4pVfFmRAi\\/l5Yjht7KqmrBz4+a6ikv2pGUlPSXI0eO\\/Bhx8CGpZ0k8aajP45s2bZo\\/evRowhAbpOw+ANITaSMJIUmjwBxyI143UhNbByrfynivLgwSHVsHnm8Nyc\\/JyTkCu3+AvRYDqPfJkyd\\/XVFRQeaLv0fSQotoyWLH7du3dWVlZfMwEKLZa65du7ZtwYIF31Cvv3HChAmF6GcBWycqKmoY\\/neO6HD5G4MeWQ+m66i2qVOnHsPP8jVr1oyAN50GFR0LwI3FxcU3tmzZUktDleYJkNWrV+dCmtWwiTHwdlWVlZVW6umSMMYOKbyh0+mGg3nxGBxqgKE4d+6ckZY3P6zkyy+\\/3HHlypUDkKx4vV4fAeYLtH0TnL2XFi5c+Ni9e\\/dioFqVIFlmZmYcwhwHvj9JgSD2U5mYmCjv379\\/0YYNG7ajT2EGgyEcIMqPHj1KQjq7ODlD2gaguXl5eQOgKWJJu\\/itGDZsWCStJ\\/hbgoNhPVhBw6EImsRB56CSID4AzMnUE+vYKUDN8TNtK4IJrVxMGzZmUEfQtsStt1bajpyWqRjGOy5cuPAkHMFXMWiiLl26tG\\/y5MkH6cAR\\/59CaF20F2enrLRPMqbfKqaeOHjN7cXGvlgP7vEdHWTKjjALfDBTRsmZGSpx0f3hfALqWdpjAm3LgDpGD4yyodzuodzChjCQ8smIjb8Sf0+aNGlqeXn5W3CYdjJTj22ePifpk\\/jEA4skNJIJAThHHTRbdigj7F7W61adzpRD9f5OWp6amroAH3uoFBpR3dqJ\\/8hOsfr94a786EoHBIDvuJnx0gitq0GKYO4\\/B7gDOnv27HrY3msP3X94wvDu1wttnyQUtMRPBHRACHXqhZY1YzIBo6Y8E523RkGy24MDHGJEQzriAD6g4Y\\/onROv3EzXdDnAIQ6ykwnXQoq4DX7EiQPMAebEAebEAebEAebEAeYULADzl2IFCfl0oiMlJSV7+vTpn5eUlJAJgUiZTObgLO4aGQyGP3goa6RfO1xu7BLADofjiLvH+qvV6ieKi4uP63S6\\/8nlcrLYTbbJ8GdYdZJUKtVAktorLysrE5+sTwTI40b6Lu3oMJlM5GzvGg5F4Im89CQyMpIcFiCLHeTdSfcFZkXLJ+9Nwg3+BCm+w9kdeKqoqPhYaN1mZOpIgrvqZDnv37\\/\\/Cmd3YKmysnLvM88887nQurHP3JEN7irArr59+xZpNJqlTqdTx1kfGHDz8vI2UXDJ7s1GwYtDed19+6hq9+7dTzU2Npby10f6h+x2u660tJT4O+TweBrSYKFl07zCGzy7\\/HJKJt4lnnj0xo0b05599tmf2Ww2RTC+OzgtLW2Ju\\/z6+voySMc34vnjYPL6a2trq6ZNm\\/YVY3PF\\/dZGoZ2tQlI8uwvwwxdECy17kclOfXH\\/b1DNkqGfN9zl0z3G\\/xLo+WQh+PZYsQ6VkX53edLIPp\\/ooDseTHTPsrjbMJRmssS9yw+E4Nu1IT4grXNv\\/vbHTBYFWnzKmxBiADef6PfmNIYvie+L5sQB5hQgFe0H9SUTWt+y2a0TBB09SJa2H9FNlSkeVbXTc1JB4V12Jw7u9PWdSU6n81CoxKsOh6Nh165ds4SWh6M1v369K\\/z0NZ5Bq6IR4E+DNE0NGVsnl\\/fKzs4m07dxdCJCxW3wo2bvlMpeQuv5Yw6wJ4LKC9UdIaLfIOcAP9rk4gB7IIvFcjPUECVz2oKXOy0Cpk58MRftrxCptLR0VHJy8ksmkykMKjuotQ3ZJ5WZmUnWasksHlnKqwNvLJ3lpy+8aJ8C7GcisSl5sg05lxsWAiZFPIVIHo5i6gkpDrqHsHTkawkti9smITQWMMTnjHRpYcAvnn0ImLZQXcAIjvics4ADzCmEqdMqmr+LmUswJw4wJw4wJw4wJw4wB5iz4NGm\\/wswAINp9H+iGUL+AAAAAElFTkSuQmCC\"},{\"color\":\"#0000ff\",\"text\":\"equipment\",\"icon\":\"data:image\\/png;base64,iVBORw0KGgoAAAANSUhEUgAAAHgAAABkCAYAAABNcPQyAAAAGXRFWHRTb2Z0d2FyZQBBZG9iZSBJbWFnZVJlYWR5ccllPAAADJVJREFUeNrsXW1sU9cZvravHePEDmnSwkJGgAqWQNtR2oaxDVGtZUgwTR6auk\\/tx0QromSkSPuzaf+mTpP4tVURUgtax09EWdiPrAjGBNqokvKRTVOyTZSSEueLJHa+bMeJffe+znvJibmx77Xvh699XumVnROfe8+9zznv13nPOQ5JkgROpUtO\\/go4wJw4wJw4wJw4wJw4wJw4wJw4wBxgThxgTrYgUa8LORyOYnouhw6dV2K4aEhraFm0W4Oz0dTUVG1FRUXL\\/Py8e3Fx0ZVKpfLqddFoNNLU1NQHXxeBF\\/BTMihob\\/TAcOjV7nwbqtf9k8nky06n8wq0Y70e14vFYh\\/7fL6fwNd54Ahw3AiQtb43rU2whQ52LJML2L0WA7jn9QIXad26dfsGBwd\\/Bl\\/xmtXA3mz3BxaBi+59isUMKnbAubm5b4LIfAVErggi15nl91v1bkNtbe1eAFlwu92LHo8n4XK5Ukq\\/g9\\/c2L179134moB2LJBoT0lFMBdblCIadanf7z8uiuLP9RyVRtLS0tJQKBR6b8uWLefgzzkS7Ql4vpSVIjpdQQ8uxMhiGUbq92CkhiWbEhh4H587d+4gPFoDsB\\/Yped704xLMQEMo+C4VAIEBt90d3f3G\\/B4W0mHu8oeYAD3oFRChCB3dHR8HR5xG3AA7QkrAC4KHQwuyVNer\\/fTbPoWRF9PtutUVlbu1VuvgroILSwshKBdii8JjK4AtLs5W5urqqreJDdrAp41VpY6GEbvL9YaBQ8ePPh9e3v7AfjpC8C7gJuAv5TJAMbneo9CvDdc+0Xg54Gble6L+nZ2dvajta5x48aN41S3TklUl4WIBqPqvtLL6erq+hH8ZDfwTuAtwM8wfukqHhsb+6me4IJU6aeO9Tzp0lql+1J7nh4fH\\/+t0nUmJiY+pM7ZiL502YloGL0vg6j7RMG3fBdcjj+gqwEcZqJJqTXu7+rt7X1h8+bNP4BrZvWZc1E8Hp\\/ZsWMHApPEyCXwJPBslnvjvbwgzj8Cf3k\\/+z9oxww836t0nVG4xrSZIroYAh0HlQpPnTp1Ed8PEyqM5QgcpFpaWv4Nn+8AVwG7C4zUpahzoU8bzebP4v8AqBiolPPw5yqAnU5nACTRzmAwiLHtCuwMuXxj3YP9VopoeCkXlMQj\\/GsP6dxn1rJA12iHk8BdB+wrgNfRdRxq7z08PFyXRZejmP4isMdMEW35CIYe\\/VpmWTgc7hWWp+mWcDZHS4+n36YoXGgq1dfXT4J66AORvJstB0t6k7A8hemhTpMwq02WBsfB8n1WyTUaGRkZYEBaFGxCpEJuZ5YHAoG99K5Fs9WipQBDT39dqRyMpX4awYs0im1DoHLuZJa53e5NbW1taHG7EGCHidkRlgIMFuazSsGF1tbWEI3gBRsCfEup\\/NixYy0EcAV9lj7AYGE+oX+j0egAo38XTbU4dSCfz3cL2hzJLN+4cWMzvW+PmWLaMoDhJdSApNqTWR6JRGSAk3bSv6yRB3QtsxwMrZ1kaLmJSxtgTLFRKh8cHBywq\\/5lQL6tAHALASyWxQgGUgT4wIEDPUyQwZYAQ+e9rVR+\\/fr1r5D+9WDkraQBhgd8woKem5vrld8RGVhJOwLs9XqvKJU3NjbKerjCrFFsGcBgYH0js2xyclIevThyE8WQ05SvHga+m1m+fv16GWB3SQOMEwxK5eFweMju+pcR03\\/NLPP7\\/S1mG1pWjWDFCYYzZ870CisRLFsDDHRfQWqlJx4YPewsSYDhwV5SCnB0dnbOMAGOpJ3RBX\\/+qlJ5U1NTswywGWLaKoCf8H9nZmZ6WP\\/XbgGOTKqurr6nFPCoq6trFlYmHkoPYJpg2KpgQYeElQiW3cVzeuJBKeBRWVnZbKY\\/bDrAoIdeUirv6+vrKSH9u2bAA5P02tvbq+ndG+4Lmz4fvLCwcGd6ejo4NjYWiMViFXJqTTAY\\/Bfp3mipAPzw4cP3RVH8z8TEhB+e2y0\\/q8fjkTtxikazYe6gGTlZDoYFRv9UksMvSxGJAMYUmYRQZOty830tOZ51nj4lpjzrmuRiyclKi59Hjx69Ar22DnxCJ\\/i+TqaRDijDzpVm6iASssvlktbKQ7apmH7iWUlVpdhnDYVCD\\/bt2\\/eZsDLJskTfU4U2QNecLBBF3wcw70qc8qLZ2dm\\/XLhwAdN1vyDQ2qZC8NJVRGMCO\\/TK3wicCiJMtb18+fKbhw8fxnRiTBmeleMCli0Ax7VFHFzdPI3AoUOH3u\\/o6MBkvRphOcszrzQfp46NeotDoy\\/IJ0+efIMMNH++9pJuAIOI\\/i6HRV+i2SfUwV4hz8kJvk9WEROuXmSiXi5LAVaa\\/+RUGIFFPcD4x4KlAIOR9SGHRF\\/q7e29IhQ4P64bwENDQ++BQ\\/9PDos+NDIy8sdgMNhP4EaFPDNMdQN427ZtEzdv3jycSCT+zuEpjEZHRz+or6\\/\\/HYE6R5zX\\/LjesWjsMD4QLfsbGhpegxHtgs+31V4DJ\\/0nJyev+Hy+TYFA4KDWNhRaHwMM4XA4PVFfW1t7tJD6NTU1r6Oro7bdw8PDFzFs2d3dfaW1tfVzAneeAh34mcon0GHE8lEnmfW4Ir5B7dYKYFD0tLW1vQp1vgy8c2BgoENLiC8SiaC+2kP1m69du\\/YdLfVB8gzR\\/XFl4K6rV68exS0kzKgP4H4grGwVsR14M\\/DTwvISVmcheBm5Pji9TAMA\\/pOah7x48eKP6eXg3hfprRri8fif1b7gzs7Ob1P97fRyqqPR6Dm19aFDvUMvGX1PjANXQ6d7N4\\/9PHbK9aHT\\/VpDXbntTwlZ1i9pxcVIP1hOXv9EzY+PHj3aT3pmjsTSNIisXrU3g9EzxNbHrRJEUfyv2vrj4+ND1OY43hvrV1RUjKqtTysyVtX3eDz\\/0FBXYgwq3XLSDA904AYran7X1dXVLKzkZKX3ecQptTw6FeZzLZFdoNXAWJUylGf9xy4NAPyimkrnz59n10PrmmxoRiTrUzU\\/AqNEjto4hZXAejifmIsVli+B9LiDkjjNOUGQkU2asB3AIOZuaTHGSfek2wVGyn3BJkQgyRJAteTBjdaoXkpr3WIZwZKaMOaGDRs2MSA7KBZrt8wOidRLGiRQMXtyVaDlOvLI1z0XzZTJBtDDd3L9xuv1siLaSfXCdkAVxSwLMKt5ctWNxWKzzMhP2hJg6NH3NbbJkYd4t4wYMav5EA+y3u0N8FrrZVkCizMgZGRg2nB14aoRrGYXeloPbW+AoYfnHMF+v7+ZaZOt5qmhA88wbpqkFuB4PC5PByaNsKBNAxjX6Wiwolk3SVBa\\/lFsxOwrIhVgQS8a4eKZNVJwa+C\\/aQDZIdiT2BFco6FjJAWDVlOaJgqV1umwRJuUsKPYbiSxAK+1yQxLzIL3JdsDLKiLSjkydbAaF8tqoljyKiNLTRQL6oVKBmA1lnQG0Fo6RjGNYNU6OBgMGhaDNh3g+fn5nD4tTTikw5XFeIqYFh2stIsBS8yOQjLAKVsDXFtbO6W04p0lZsLBxbSt6EcwM9HwGOBcIhos6CFh9Y5+kq0BpriFGn1quwmHfCYamB0NkkaNXrMBRrCy+rQ6TThY5WJpmmhgJvkN3bLCVIBBL01l+38gEGgQMvavgDrb1Fz79OnT8q7qorxNoNq6TOfKVA81Gu\\/92HbIde\\/+\\/v4how0sWW6admZDPB4\\/lCs\\/6dKlSz8UlvOiNty7d28XuEmfqT0G58SJE3ggBr7Y9dFotF3rSWUA1LeE5ZywutHR0a+pPUMRE\\/7o3hiarAb9+qtc9xKWEwSfE+hMCqPwMvtYHTxcI2dvxRPDMF0Hz\\/DVZMKmUjPQifpFUfR7PJ5dmk1gqA8dZQDUQtLr9X5VS12cMgR+6Ha7q4Cfy2VB4+mqwnL+1hjwlBaANeFi9rlJmKNlxFm\\/diJctUCJ7ZjvjIl9M0YBbLqvCQCX\\/SK1RCIxIxgcg7YM4Fwx6XIgxoIuPYDVptGWMhmZJmu5Dp6ent4O7tD\\/yhVcNMbAAMRVGJjcPi4sn4uo5eCv4h7BOPkPbkJfuQKMi8yYAEfCyCiWJQDjw4Er88tyHb106CaCG6NRbLjRY8XhlK5IJPI26ONIuWxwhoEYOg8ZVz9upyiZ02i8rDw\\/2H327NnGI0eOvAVO\\/341E+R2JEzIGxsb66HziJcouIGRLFygljD6\\/GArAZZjzrgGtoo+XYJ987Gy4kLWMoKbPo+YwJbyGcFaSLT4oRcZfeQpA4Dl01RNy\\/cWi+ThbXWMrJ1IN4BtesRRyRPf6Y4DzIkDzIkDzIkDzIkDzIkDzIkDXE70fwEGAHkIZWfZPrykAAAAAElFTkSuQmCC\"}]}}";

                        DataResponse dataResponse = null;

                        try {
                            dataResponse = objectMapper.readValue(jsonString, DataResponse.class);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                        tv_showJSON.setText(dataResponse.getStatus());

//                        dataResponse = objectMapper.readValues(jsonStr, DataResponse.class);
//                        tv_showJSON.setText(jsonStr);
//                        tv_showJSON.setText(dataResponse.getStatus());


                        *//*try {
                            JsonNode rootNode = objectMapper.readTree(new byte[]{response.body().getAsByte()});
                            JsonNode statusNode = rootNode.path("status");
                            tv_showJSON.setText(statusNode.asText());

                        } catch (JsonProcessingException e) {
                            e.printStackTrace();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }*//*
                    }

                    @Override
                    public void onFailure(Call<JsonObject> call, Throwable t) {
                        Toast.makeText(MainActivity.this, "Error ;=(\n" + t.getMessage(), Toast.LENGTH_LONG).show();
                    }
                });*/
            }
        };
        b_version.setOnClickListener(onClickListener_VerSion);


//        final View.OnClickListener onClickListener_Ver = new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Call<DataResponse> call = api_JSON.getData();
//                call.enqueue(new Callback<DataResponse>() {
//                    @Override
//                    public void onResponse(Call<DataResponse> call, Response<DataResponse> response) {
//                        try {
//                            String status = response.body().getStatus();
//                            float actual_version = response.body().getActual_version();
//                            ArrayList<Initial_screen> initial_screens = response.body().getData;
//
//                            for (int i = 0; i < initial_screens.size(); i++) {
////                            Log.d("onResponse: " + "\ncolor: " + initial_screens.get("status"));
//                            }
//                        } catch (Exception e) {
//                            Toast.makeText(MainActivity.this, "Error ;=(\n" + e.getMessage(), Toast.LENGTH_LONG).show();
//                        }
//                    }
//
//                    @Override
//                    public void onFailure(Call<DataResponse> call, Throwable t) {
//                    }
//                });
//            }
//        };
//        b_version.setOnClickListener(onClickListener_Ver);


        final View.OnClickListener onClickListener_getJSON = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                api.loginAccount(usedDataForPOST()).enqueue(new Callback<JsonObject>() {
                    @Override
                    public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                        try {
                            JSONObject jsonResponse = new JSONObject(String.valueOf(response.body()));


//                            for (int i = 0; i < response.body().size(); i++) {
//                                status = response.body().get("status").toString();
//                                actual_version = response.body().get("actual_version").getAsDouble();
//                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

//                        tv_showJSON.setText("Response POST:\n" + response.body().toString() +
//                                "\nStatus: " + status + "\nActual version: " + actual_version
//                        );

                        tv_showDecriptJSON.setText(response.body().toString() + "\n"
                                        + "\nStatus: " + response.body().get("status")
                                        + "\nActual version: " + response.body().get("actual_version")
                                        + "\nConfiguartion: " + response.body().get("configuartion")

//                                +"\nConfiguartion: " + response.body().get("configuartion")
                        );
                    }

                    @Override
                    public void onFailure(Call<JsonObject> call, Throwable t) {
                        Toast.makeText(MainActivity.this, "Error ;=(\n" + t.getMessage(), Toast.LENGTH_LONG).show();
                    }
                });
            }
        };
        b_decriptJSON.setOnClickListener(onClickListener_getJSON);


////        Getting JSON --- Start ---
//        final View.OnClickListener onClickListener_getJSON = new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                api.loginAccount(usedDataForPOST()).enqueue(new Callback<JsonObject>() {
//                    @Override
//                    public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
//                        try {
//                            JSONObject jsonResponse = new JSONObject(String.valueOf(response.body()));
//
//                            for (int i = 0; i < response.body().size(); i++) {
//                                status = response.body().get("status").toString();
//                                actual_version = response.body().get("actual_version").getAsDouble();
//                            }
//
//                        } catch (JSONException e) {
//                            e.printStackTrace();
//                        }
//
//                        tv_showJSON.setText("Response POST:\n" + response.body().toString() +
//                                "\nStatus: " + status + "\nActual version: " + actual_version
//                        );
//                    }
//
//                    @Override
//                    public void onFailure(Call<JsonObject> call, Throwable t) {
//                        Toast.makeText(MainActivity.this, "Error ;=(\n" + t.getMessage(), Toast.LENGTH_LONG).show();
//                    }
//                });
//            }
//        };
//        b_getJSON.setOnClickListener(onClickListener_getJSON);
//        //    Getting JSON --- End ---


////    Getting JSON --- Start ---
//        final View.OnClickListener onClickListener_getJSON = new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                api.loginAccount(usedDataForPOST()).enqueue(new Callback<JsonObject>() {
//                    @Override
//                    public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
//                        try {
//                            JSONObject jsonResponse = new JSONObject(String.valueOf(response.body()));
//
//                            for (int i = 0; i < response.body().size(); i++) {
//                                status = response.body().get("status").toString();
//                                actual_version = response.body().get("actual_version").getAsDouble();
//                            }
//
//                        } catch (JSONException e) {
//                            e.printStackTrace();
//                        }
//
//                        tv_showJSON.setText("Response POST:\n" + response.body().toString() +
//                                "\nStatus: " + status + "\nActual version: " + actual_version
//                        );
//                    }
//
//                    @Override
//                    public void onFailure(Call<JsonObject> call, Throwable t) {
//                        Toast.makeText(MainActivity.this, "Error ;=(\n" + t.getMessage(), Toast.LENGTH_LONG).show();
//                    }
//                });
//            }
//        };
//        b_getJSON.setOnClickListener(onClickListener_getJSON);
//        //    Getting JSON --- End ---


        //    Getting JSON --- Start ---
//        Call<JsonObject> call = api.loginAccount();


        //    Getting JSON --- End ---
    }

    //..Method for filling users information, for POST ........Start.........
    public Map<String, String> usedDataForPOST() {
        final Map<String, String> parametersFromUser = new HashMap<>();

        parametersFromUser.put(action, value);

        return parametersFromUser;
    }
//..Method for filling users information, for POST ........End .........
}
