abstract class Employe{
    
    private  String nom;
    private  String prenom;
    private  String matricule;
    private  int age;
    private  String date;
    
    // creation du constructeur
    public Employe (String nom, String prenom, String matricule, int age, String date){
        this.nom = nom;
        this.prenom = prenom;
        this.matricule = matricule;
        this.age =age;
        this.date =date;
    }

    //
    public abstract  double calculerSalaire();
    
          public String getNom(){
        return "L'employe" + prenom + nom;
    }
}


public class Commercial extends Employe {
    
    private double chiffreAffaire;
  
        //creation d'un constructeur
    public Commercial(String nom, String prenom, String matricule, int age, String date, double chiffreAffaire) {
        super(nom, prenom, matricule, age, date);
        this.chiffreAffaire = chiffreAffaire;  
    }
    public double getChiffreAffaire(){
        return chiffreAffaire;
    }

    @Override
    public double calculerSalaire() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}

class Vendeur extends Commercial {
    
    // creation d'un contructeur
  public Vendeur(String nom, String prenom, String matricule, int age, String date, double chiffreAffaire) {
        super(nom, prenom, matricule, age, date,chiffreAffaire);
    }
  @Override
     public double calculerSalaire() { 
        return 0.2*getChiffreAffaire() + 100;
    }
    
}

class Representateur extends Commercial {
    
   
    public Representateur(String nom, String prenom, String matricule, int age, String date, double chiffreAffaire) {
        super(nom, prenom, matricule, age, date,chiffreAffaire);
    }
    
    // méthode pour calculer le salaire menseul d'un representateur
    @Override
     public double calculerSalaire() { 
        return 0.2*getChiffreAffaire() + 200;
    }
    
}

public class Producteur extends Employe {
     
    private int nombreUnite;
   
    public Producteur(String nom, String prenom, String matricule, int age, String date, int nombreUnite) {
        super(nom, prenom, matricule, age, date);
        this.nombreUnite = nombreUnite;
    }
        public int getNombreUnite(){
        return nombreUnite ;
    }

    //le calcul de son salaire
    
    @Override
    public double calculerSalaire(){
        return getNombreUnite()*5;
    }    
}


class ProducteurRisque extends Producteur implements Syndicalistes{

    public ProducteurRisque(String nom, String prenom, String matricule, int age, String date, int nombreUnite) {
        super(nom, prenom, matricule, age, date, nombreUnite);
        
    }
    @Override
    public double calculerSalaire(){
        return super.calculerSalaire() + prime;
    }    
}

public interface Syndicalistes {

    public double prime = 25000;
    
}

/**
 *
 * @author PADAMOUND GARGA LAURENT
 */
public class Manutentionnaire extends Employe {
    
    private final int nombreHeur;
    
   public Manutentionnaire(String nom, String prenom, String matricule, int age, String date, int nombreHeur) {
        super(nom, prenom, matricule, age, date);
        this.nombreHeur = nombreHeur;
    }
   
   public int getNombreHeur(){
       return nombreHeur;
   }
   
    //le calcul de son salaire

        @Override
    public double calculerSalaire(){
        return getNombreHeur()*65;
    }
    
    
}

class ManutentionnaireRisque extends Manutentionnaire implements Syndicalistes {

    public ManutentionnaireRisque(String nom, String prenom, String matricule, int age, String date, int nombreHeur) {
        super(nom, prenom, matricule, age, date, nombreHeur);
    }
    
    @Override
    public double calculerSalaire(){
        return super.calculerSalaire() + prime;
    }
          
}

public class Personne {
    
    private final Employe[] TAB;
    
    // nE nombre des employés de l'entreprise
    private int nEmploye; 
    
    // max nombre maximal d'employés que peut comtenir l'entreprise
    private static final int max = 30 ;
    
   
    
    // un constructeur 
    public Personne (){
        //mon tableau est appelé TAB.
        
        TAB = new Employe[ max ];
        nEmploye = 0;
    }
    
    public void ajouterEmploye(Employe e){
        
        ++ nEmploye;
        
        if(nEmploye <= max)
        {
            TAB[ nEmploye-1 ] = e;
        } 
        else 
            System.out.println("pas plus de " + max + " employés ");
        
    }
    
    public double salaireMoyen(){
        double somme = 0.0;
        
        for(int i = 0; i< nEmploye; i++){
            somme += TAB[i].calculerSalaire();
        }
        return somme/nEmploye;
    }
    
  public void afficherSalaires() {
        for (int i = 0; i < nEmploye; i++) {
            System.out.println(TAB[i].getNom() + " gagne "
                    + TAB[i].calculerSalaire() + " francs.");
        }
    }
}

class Salaires {
    public static void main(String[] args) {
        
        Personne pers = new Personne();
        
        pers.ajouterEmploye(new Vendeur(" PAD12VD ", " TOTO ", " LEBETE ", 25, " 2012 ", 30000));
        pers.ajouterEmploye(new Representateur(" PAD15RP ", " MOMO ", " OMO ", 29, " 2015 ", 20000));
        pers.ajouterEmploye(new Producteur(" PAD95PR ", " LOTO ", " DELAGEB ", 30, " 1995 ", 1000));
        pers.ajouterEmploye(new Manutentionnaire(" PAD99MT ", " TEFY ", " LACHEVRE ", 32, " 1999 ", 45));
        pers.ajouterEmploye(new ProducteurRisque(" PAD00PR ", " BRODO ", " MOUTON ", 47, " 2000 ", 1000));
        pers.ajouterEmploye(new ManutentionnaireRisque(" PAD16MT ", " LATIFA ", " TORO ", 29, " 2016 ", 45));
        
        pers.afficherSalaires();

        System.out.println("Le salaire moyen dans l'entreprise est de " + pers.salaireMoyen()
                + " francs.");
        
    }
    
}







