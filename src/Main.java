import javax.swing.*;
import org.jpl7.Term;
import org.jpl7.Query;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.text.Normalizer;
import java.util.ArrayList;
import java.util.List;

public class Main {
    static JCheckBox[] checkGeneros;
    static JCheckBox[] checkIdiomas;
    static JComboBox<String> comboDuracion;
    static JComboBox<String> comboClasificacion;
    static JComboBox<String> comboPlataforma;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Filtro de Películas");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(800, 750);
            frame.setLocationRelativeTo(null);

            JPanel mainPanel = new JPanel(new BorderLayout());
            JPanel formPanel = new JPanel(new GridBagLayout());
            formPanel.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));
            GridBagConstraints gbc = new GridBagConstraints();
            gbc.insets = new Insets(5, 5, 50, 5);
            gbc.anchor = GridBagConstraints.EAST;
            gbc.fill = GridBagConstraints.NONE;

            Font labelFont = new Font("SansSerif", Font.BOLD, 14);

            // ========== GÉNERO ==========
            gbc.gridx = 0;
            gbc.gridy = 0;
            JLabel generoLabel = new JLabel("Género de la película:");
            generoLabel.setFont(labelFont);
            formPanel.add(generoLabel, gbc);

            gbc.gridx = 1;
            gbc.anchor = GridBagConstraints.WEST;
            String[] generos = {"Acción", "Aventura", "Animación", "Biografía", "Ciencia Ficción",
                    "Comedia", "Crimen", "Documental", "Drama", "Fantasía",
                    "Histórica", "Musical", "Romance", "Suspenso", "Terror"};
            JPanel generoPanel = new JPanel(new GridLayout(5, 3, 10, 5));
            checkGeneros = new JCheckBox[generos.length];
            for (int i = 0; i < generos.length; i++) {
                checkGeneros[i] = new JCheckBox(generos[i]);
                generoPanel.add(checkGeneros[i]);
            }
            formPanel.add(generoPanel, gbc);

            // ========== DURACIÓN ==========
            gbc.gridx = 0;
            gbc.gridy++;
            gbc.anchor = GridBagConstraints.EAST;
            JLabel duracionLabel = new JLabel("Duración de la película:");
            duracionLabel.setFont(labelFont);
            formPanel.add(duracionLabel, gbc);

            gbc.gridx = 1;
            gbc.anchor = GridBagConstraints.WEST;
            String[] duraciones = {"Menos de 45 min", "45 - 60 min", "60 - 90 min", "90 - 120 min", "Más de 120 min"};
            comboDuracion = new JComboBox<>(duraciones);
            comboDuracion.setPreferredSize(new Dimension(200, 25));
            formPanel.add(comboDuracion, gbc);

            // ========== IDIOMAS ==========
            gbc.gridx = 0;
            gbc.gridy++;
            gbc.anchor = GridBagConstraints.EAST;
            JLabel idiomaLabel = new JLabel("Idiomas disponibles:");
            idiomaLabel.setFont(labelFont);
            formPanel.add(idiomaLabel, gbc);

            gbc.gridx = 1;
            gbc.anchor = GridBagConstraints.WEST;
            String[] idiomas = {"Español", "Inglés", "Francés", "Japonés", "Coreano"};
            JPanel idiomaPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 5));
            checkIdiomas = new JCheckBox[idiomas.length];
            for (int i = 0; i < idiomas.length; i++) {
                checkIdiomas[i] = new JCheckBox(idiomas[i]);
                idiomaPanel.add(checkIdiomas[i]);
            }
            formPanel.add(idiomaPanel, gbc);

            // ========== CLASIFICACIÓN ==========
            gbc.gridx = 0;
            gbc.gridy++;
            gbc.anchor = GridBagConstraints.EAST;
            JLabel clasificacionLabel = new JLabel("Clasificación de edad:");
            clasificacionLabel.setFont(labelFont);
            formPanel.add(clasificacionLabel, gbc);

            gbc.gridx = 1;
            gbc.anchor = GridBagConstraints.WEST;
            String[] clasificaciones = {"apta_todo_publico", "mayores_13", "mayores_18"};
            comboClasificacion = new JComboBox<>(clasificaciones);
            comboClasificacion.setPreferredSize(new Dimension(200, 25));
            formPanel.add(comboClasificacion, gbc);

            // ========== PLATAFORMA ==========
            gbc.gridx = 0;
            gbc.gridy++;
            gbc.anchor = GridBagConstraints.EAST;
            JLabel plataformaLabel = new JLabel("Plataforma de preferencia:");
            plataformaLabel.setFont(labelFont);
            formPanel.add(plataformaLabel, gbc);

            gbc.gridx = 1;
            gbc.anchor = GridBagConstraints.WEST;
            String[] plataformas = {"Netflix", "Disney+", "Prime Video", "Cine", "HBO Max"};
            comboPlataforma = new JComboBox<>(plataformas);
            comboPlataforma.setPreferredSize(new Dimension(200, 25));
            formPanel.add(comboPlataforma, gbc);

            // ========== BOTÓN ==========
            gbc.gridy++;
            gbc.gridx = 1;
            gbc.anchor = GridBagConstraints.CENTER;
            JButton botonBuscar = new JButton("Buscar películas");
            botonBuscar.setFont(new Font("SansSerif", Font.BOLD, 16));
            botonBuscar.setBackground(new Color(60, 120, 220));
            botonBuscar.setForeground(Color.WHITE);
            botonBuscar.setFocusPainted(false);
            botonBuscar.setPreferredSize(new Dimension(200, 40));
            formPanel.add(botonBuscar, gbc);

            //ActionPerformed 
            botonBuscar.addActionListener((ActionEvent e) -> {
                List<String> generosSeleccionados = new ArrayList<>();
                for (JCheckBox cb : checkGeneros) {
                    if (cb.isSelected()) {
                        generosSeleccionados.add(normalizar(cb.getText())); //seleccion seleccion de checkboxs
                    }
                }

                String duracion = normalizar((String) comboDuracion.getSelectedItem()); //seleccion

                List<String> idiomasSeleccionados = new ArrayList<>();
                for (JCheckBox cb : checkIdiomas) {
                    if (cb.isSelected()) {
                        idiomasSeleccionados.add(normalizar(cb.getText()));     //seleccion de checkboxs
                    }
                }

                String clasificacion = normalizar((String) comboClasificacion.getSelectedItem()); //seleccion
                String plataforma = normalizar((String) comboPlataforma.getSelectedItem()); //seleccion

                // Testeo 1 para ver que los datos se impriman correctamente en el terminal.
                //System.out.println("🎬 Filtros seleccionados:");
                //System.out.println("Géneros: " + generosSeleccionados);
                //System.out.println("Duración: " + duracion);
                //System.out.println("Idiomas: " + idiomasSeleccionados);
                //System.out.println("Clasificación: " + clasificacion);
                //System.out.println("Plataforma: " + plataforma);

                try {
                    String ruta = "Prolog/peliculas.pl"; //ruta del archivo prolog
                    // realiza la conexión con el archivo peliculas.pl
                    Query cargar = new Query("consult('" + ruta.replace("\\", "/") + "')"); //reemplaza \\ por / para evitar errores.
                    if (!cargar.hasSolution()) {
                        System.out.println("No se pudo cargar el archivo Prolog."); // causa un exception 
                        return;
                    }
                    // transforma las listas de java en listas de prolog.
                    String generosProlog = "[" + 
                        String.join(", ", //une los ementos con comas [ , , ,] 
                            generosSeleccionados.stream()  //transformando en flujo
                                .map(s -> "'" + s + "'") //coloca las comillas simples en cada elemento '_'
                                .toList() // transformamos de nuevo en una Lista
                        ) + "]";
                    //lo mismo
                    String idiomasProlog = "[" + String.join(", ", idiomasSeleccionados.stream().map(s -> "'" + s + "'").toList()) + "]";
                    //realiza la consulta en prolog:
                    // recomendar_pelicula(Titulo, Generos, Idiomas, DuracionRango, Clasificacion, Plataforma).
                    String consulta = String.format(
                        "recomendar_pelicula(Titulo, %s, %s, '%s', '%s', '%s')", //regla principal
                        generosProlog,
                        idiomasProlog,
                        duracion,
                        clasificacion,
                        plataforma
                    );
                //Testeo 2 para validar si los datos de la consulta son correctos
                // System.out.println("Consulta Prolog generada: " + consulta);
                    
                Query q = new Query(consulta);
                StringBuilder resultado = new StringBuilder();
                while (q.hasMoreSolutions()) {
                    Term t = q.nextSolution().get("Titulo");
                    resultado.append("🎬 ").append(t.toString()).append("\n");
                }

                if (resultado == null || resultado.length() == 0) {
                    JOptionPane.showMessageDialog(null, "No se encontraron películas con esos filtros.");
                } else {
                    JOptionPane.showMessageDialog(null, resultado.toString(), "Películas recomendadas", JOptionPane.INFORMATION_MESSAGE);
                }


                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "❌ Error al ejecutar Prolog: " + ex.getMessage());
                }
            });

            mainPanel.add(formPanel, BorderLayout.CENTER);
            frame.add(new JScrollPane(mainPanel));
            frame.setVisible(true);
        });
    }

    public static String normalizar(String texto) { 
        // Quitar caracteres especiales como "é, ó, etc" y aplica lower. 
        //Con el fin de que sea entendible para el prolog
        return Normalizer.normalize(texto, Normalizer.Form.NFD)
            .replaceAll("[\\p{InCombiningDiacriticalMarks}]", "")
            .toLowerCase();
    }
}
