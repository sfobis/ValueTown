package mobile.valuetown.bdd;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class StoreBdd {

    private static final int VERSION_BDD = 1;
    private static final String NOM_BDD = "app.db";

    public static final String TABLE_STORE = "table_store";
    public static final String COL_ID = "id";
    public static final int NUM_COL_ID = 0;
    public static final String COL_VILLE = "ville";
    public static final int NUM_COL_Ville = 1;
    public static final String COL_CODE = "code";
    public static final int NUM_COL_CODE = 2;

    private SQLiteDatabase bdd;

    private Bdd maBase;

    public StoreBdd(Context context){
        //On créer la BDD et sa table
        maBase = new Bdd(context, NOM_BDD, null, VERSION_BDD);
    }

    public void open(){
        //on ouvre la BDD en écriture
        bdd = maBase.getWritableDatabase();
    }

    public void close(){
        //on ferme l'accès à la BDD
        bdd.close();
    }

    public SQLiteDatabase getBDD(){
        return bdd;
    }

    public long insertStore(Store store){
        //Création d'un ContentValues (fonctionne comme une HashMap)
        ContentValues values = new ContentValues();
        //on lui ajoute une valeur associé à une clé (qui est le nom de la colonne dans laquelle on veut mettre la valeur)
        values.put(COL_VILLE, store.getVille());
        values.put(COL_CODE, store.getCode());
        //on insère l'objet dans la BDD via le ContentValues
        return bdd.insert(TABLE_STORE, null, values);
    }

    public int updateStore(int id, Store store){
        //La mise à jour d'un store dans la BDD fonctionne plus ou moins comme une insertion
        //il faut simplement préciser quelle store on doit mettre à jour grâce à l'ID
        ContentValues values = new ContentValues();
        values.put(COL_VILLE, store.getVille());
        values.put(COL_CODE, store.getCode());
        return bdd.update(TABLE_STORE, values, COL_ID + " = " +id, null);
    }

    public int removeStoreWithID(int id){
        //Suppression d'un store de la BDD grâce à l'ID
        return bdd.delete(TABLE_STORE, COL_ID + " = " +id, null);
    }

    public Store getStoreWithVille(String ville){
        //Récupère dans un Cursor les valeur correspondant à un store contenu dans la BDD (ici on sélectionne le store grâce à sa ville)
        Cursor c = bdd.query(TABLE_STORE, new String[] {COL_ID, COL_VILLE, COL_CODE}, COL_CODE + " LIKE \"" + ville +"\"", null, null, null, null);
        return cursorToStore(c);
    }

    public Store getStoreWithID(int ID){
        //Récupère dans un Cursor les valeur correspondant à un store contenu dans la BDD (ici on sélectionne le store grâce à son ID)
        Cursor c = bdd.query(TABLE_STORE, new String[] {COL_ID, COL_VILLE, COL_CODE}, COL_ID + " LIKE \"" + ID +"\"", null, null, null, null);
        return cursorToStore(c);
    }

    //Cette méthode permet de convertir un cursor en un store
    private Store cursorToStore(Cursor c){
        //si aucun élément n'a été retourné dans la requête, on renvoie null
        if (c.getCount() == 0)
            return null;

        //Sinon on se place sur le premier élément
        c.moveToFirst();
        //On créé un store
        Store store = new Store();
        //on lui affecte toutes les infos grâce aux infos contenues dans le Cursor
        store.setId(c.getInt(NUM_COL_ID));
        store.setVille(c.getString(NUM_COL_Ville));
        store.setCode(c.getString(NUM_COL_CODE));
        //On ferme le cursor
        c.close();

        //On retourne le store
        return store;
    }
}