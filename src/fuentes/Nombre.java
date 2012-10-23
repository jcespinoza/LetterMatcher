package fuentes;

public class Nombre {

	private Nombre(){}
	
    public static String nombres[] = {"Pascualito", "Super Mario Bros", "Mr Pac Man", "Gatito Miau", "Zulema Rivera Quebrada",
                                "Panchito Tornillo", "Donia Pascuala", "Maria Jimena Smith", "Michael Essien White", "Alex Rocola",
                                "Patricio Estrella", "Bob Sponge Perez", "Cecilia Tejado", "Katy Perryta", "Britney Spiral",
                                "William Chongo Wong", "Rodrigo Uong Taladro", "Luigi Lombardi", "Ash de pueblo paleta", "Misty",
                                "Zoey 102", "James Bond 006.9", "Dany Alvestidor", "Henry Segueta", "Carlos MArtillo",
                                "Ada Wong", "Tenshi Jamon", "Steve Works", "Bill Doors", "Mike Crosoft",
                                "Calamardo Tentacu Lopez", "Patito de Hule", "Liu Kain Bolt", "Usain Voltio", "Alberto Einstein",
                                "Jorge Bush", "Vanesa Krauser", "Bertha Melano", "Freddy Krugger", "Javier Tos Elo",
                                "Kournikova Ginares", "Hop Solo Conmigo", "Forever Alonso", "Karen Prudencia Almedraez", "Pikachu Evolucionado",
                                "Niyo Lani Yolez", "Marco Antonio Lunis", "Bruno Diaz Murcielago", "Katara Tas", "Monica Lewinski",
                                "Benedicto 26", "Juan Pablo Neruda", "El Principito", "Jose Porfirio Caperucito", "Caperucita Roja"};
    
    public static String randomNombre(){
        int n = Ut.generarNumero(Nombre.nombres.length - 1);
        return nombres[n];
    }
}