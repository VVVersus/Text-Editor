package editor;

import javax.swing.*;
import java.awt.*;

import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TextEditor extends JFrame {

    private final List<SearchResult> searchResults = new ArrayList<>();
    private ListIterator<SearchResult> currentSearchResult;
    private boolean forward;

    public TextEditor() {
        setTitle("VV text editor");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);

        setLocationRelativeTo(null);

        initComponents();

        setVisible(true);
    }

    private void initComponents() {
        System.out.println(Thread.currentThread().getName() + (SwingUtilities.isEventDispatchThread() ? " is Event Dispatcher Thread" : ""));
        //DECLARE ELEMENTS -------------------------------------------------------------------------
        JPanel toolBar = new JPanel();
        FlowLayout experimentLayout = new FlowLayout();
        JFileChooser fileChooser = new JFileChooser();

        //FIELDS
        JTextField eSearch = new JTextField();
        JTextArea textArea = new JTextArea();
        JScrollPane scrollableTextArea = new JScrollPane(textArea);
        JCheckBox cRegEx = new JCheckBox();

        //BUTTONS
        JButton bLoadFile = new JButton();
        JButton bSaveFile = new JButton();
        JButton bSearch = new JButton();
        JButton bPreviousMatch = new JButton();
        JButton bNextMatch = new JButton();

        //MENUS
        JMenuBar mainMenu = new JMenuBar();
        JMenu menuFile = new JMenu("File");
        JMenu menuSearch = new JMenu("Search");

        //MENU ITEMS
        JMenuItem miLoad = new JMenuItem("Load");
        JMenuItem miSave = new JMenuItem("Save");
        JMenuItem miExit = new JMenuItem("Exit");
        JMenuItem miSearch = new JMenuItem("Start search");
        JMenuItem miNextMatch = new JMenuItem("Next match");
        JMenuItem miPreviousMatch = new JMenuItem("Previous search");
        JMenuItem miRegEx = new JMenuItem("Use regular expressions");

        //ACTIONS ---------------------------------------------------------------------------------------------------------
        //OPEN
        ActionListener onOpen = actionEvent  -> {
            fileChooser.showOpenDialog(null);
            try {
                String content = new String(Files.readAllBytes(Paths.get(fileChooser.getSelectedFile().getAbsolutePath())));
                textArea.setText(content);
            } catch (IOException e) {
                textArea.setText("");
                System.out.println(e.getMessage());
            }
        };
        //SAVE
        ActionListener onSave = actionEvent  -> {
            fileChooser.showSaveDialog(null);
            try (PrintWriter writer = new PrintWriter(fileChooser.getSelectedFile().getAbsolutePath())) {
                writer.write(textArea.getText());
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        };
        //SEARCH
        ActionListener onSearch = actionEvent -> {
            searchResults.clear();
            String text = textArea.getText();
            String foundText = eSearch.getText();
            int index = 0;

            if (cRegEx.isSelected()) {
                Pattern pattern = Pattern.compile("(" + eSearch.getText() + ")", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE);
                Matcher matcher = pattern.matcher(text);
                while (matcher.find()) {
                    searchResults.add(new SearchResult(matcher.start(), matcher.group()));
                }
            } else {
                while ((index = text.indexOf(foundText, index)) > 0) {
                    searchResults.add(new SearchResult(index++, foundText));
                }
            }
            currentSearchResult = searchResults.listIterator();
            forward = true;
            if (currentSearchResult.hasNext()) {
                SearchResult current = currentSearchResult.next();
                index = current.getIndex();
                foundText = current.getFoundText();
                textArea.setCaretPosition(index + foundText.length());
                textArea.select(index, index + foundText.length());
                textArea.grabFocus();
            }
        };
        //PREVIOUS MATCH
        ActionListener onPreviousSearch = actionEvent -> {
            if (searchResults.isEmpty()) {
                return;
            }
            String foundText;
            int index;
            if (forward) {
                currentSearchResult.previous();
                forward = false;
            }
            SearchResult current;
            if (!currentSearchResult.hasPrevious()) {
                while (currentSearchResult.hasNext()) {
                    currentSearchResult.next();
                }
            }

            current = currentSearchResult.previous();

            index = current.getIndex();
            foundText = current.getFoundText();
            textArea.setCaretPosition(index + foundText.length());
            textArea.select(index, index + foundText.length());
            textArea.grabFocus();
        };
        //NEXT MATCH
        ActionListener onNextSearch = actionEvent -> {
            String foundText;
            int index;
            if (!forward) {
                currentSearchResult.next();
                forward = true;
            }
            SearchResult current;
            if (!currentSearchResult.hasNext()) {
                currentSearchResult.previous();
            }
            current = currentSearchResult.next();
            index = current.getIndex();
            foundText = current.getFoundText();
            textArea.setCaretPosition(index + foundText.length());
            textArea.select(index, index + foundText.length());
            textArea.grabFocus();
        };
        //CHECK REGEX
        ActionListener onRegExCheck = actionEvent -> cRegEx.setSelected(!cRegEx.isSelected());
        //EXIT
        ActionListener onExit = actionEvent -> dispose();

        //INITIALIZE ELEMENTS SETTINGS --------------------------------------------------------------------
        //TOOLBAR
        toolBar.setLayout(experimentLayout);
        add(toolBar, BorderLayout.NORTH);

        textArea.setName("TextArea");
        textArea.setBounds(10, 10, 260, 200);
        add(textArea);

        scrollableTextArea.setName("ScrollPane");
        scrollableTextArea.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        getContentPane().add(scrollableTextArea);

        experimentLayout.setAlignment(FlowLayout.LEFT);

        fileChooser.setName("FileChooser");
        add(fileChooser);

        //LOAD BUTTON --------------------------------------------------------------------------------------------------
        bLoadFile.setName("OpenButton");
        bLoadFile.setIcon(new ImageIcon("open.png"));
        bLoadFile.addActionListener(onOpen);
        toolBar.add(bLoadFile);

        //SAVE BUTTON --------------------------------------------------------------------------------------------------
        bSaveFile.setName("SaveButton");
        bSaveFile.setIcon(new ImageIcon("save.png"));
        bSaveFile.addActionListener(onSave);
        toolBar.add(bSaveFile);

        //SEARCH FIELD --------------------------------------------------------------------------------------------------
        eSearch.setName("SearchField");
        eSearch.setPreferredSize(new Dimension(300, 43));
        toolBar.add(eSearch);

        //REGEX CHECKBOX --------------------------------------------------------------------------------------------------
        cRegEx.setName("UseRegExCheckbox");
        cRegEx.setText("Use RegEx");

        //SEARCH BUTTON --------------------------------------------------------------------------------------------------
        bSearch.setName("StartSearchButton");
        bSearch.setIcon(new ImageIcon("search.png"));
        bSearch.addActionListener(onSearch);

        toolBar.add(bSearch);

        //PREVIOUS MATCH BUTTON --------------------------------------------------------------------------------------------------
        bPreviousMatch.setName("PreviousMatchButton");
        bPreviousMatch.setIcon(new ImageIcon("previous.png"));
        bPreviousMatch.addActionListener(onPreviousSearch);
        toolBar.add(bPreviousMatch);

        //NEXT MATCH BUTTON --------------------------------------------------------------------------------------------------
        bNextMatch.setName("NextMatchButton");
        bNextMatch.setIcon(new ImageIcon("next.png"));
        bNextMatch.addActionListener(onNextSearch);
        toolBar.add(bNextMatch);

        toolBar.add(cRegEx);

        //MENU FILE --------------------------------------------------------------------------------------------------
        menuFile.setName("MenuFile");

        miLoad.setName("MenuOpen");
        miLoad.addActionListener(onOpen);

        miSave.setName("MenuSave");
        miSave.addActionListener(onSave);

        miExit.setName("MenuExit");
        miExit.addActionListener(onExit);

        menuFile.add(miLoad);
        menuFile.add(miSave);
        menuFile.addSeparator();
        menuFile.add(miExit);

        mainMenu.add(menuFile);

        //MENU SEARCH --------------------------------------------------------------------------------------------------
        menuSearch.setName("MenuSearch");

        miSearch.setName("MenuStartSearch");
        miSearch.addActionListener(onSearch);

        miPreviousMatch.setName("MenuPreviousMatch");
        miPreviousMatch.addActionListener(onPreviousSearch);

        miNextMatch.setName("MenuNextMatch");
        miNextMatch.addActionListener(onNextSearch);

        miRegEx.setName("MenuUseRegExp");
        miRegEx.addActionListener(onRegExCheck);

        menuSearch.add(miSearch);
        menuSearch.add(miPreviousMatch);
        menuSearch.add(miNextMatch);
        menuSearch.add(miRegEx);

        mainMenu.add(menuSearch);

        setJMenuBar(mainMenu);
    }
}

class SearchResult {
    private final int index;
    private final String foundText;

    public SearchResult(int index, String foundText) {
        this.index = index;
        this.foundText = foundText;
    }

    public int getIndex() {
        return index;
    }

    public String getFoundText() {
        return foundText;
    }
}

class SearchTask extends SwingWorker<List<SearchResult>, SearchResult> {

    private JTextArea textArea;
    private String target;
    private String source;

    @Override
    protected List<SearchResult> doInBackground() throws Exception {
        
        return null;
    }
}
