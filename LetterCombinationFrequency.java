import java.util.*;
import java.io.*;

public class LetterCombinationFrequency {
    private HashMap<String, Integer> twoLetterFreq;
    private HashMap<String, Integer> threeLetterFreq;
    private HashMap<String, Integer> fourLetterFreq;
    private HashMap<String, Integer> fiveLetterFreq;
    private HashMap<String, String> compressionMap;

    public LetterCombinationFrequency() {
        twoLetterFreq = new HashMap<>();
        threeLetterFreq = new HashMap<>();
        fourLetterFreq = new HashMap<>();
        fiveLetterFreq = new HashMap<>();
        compressionMap = new HashMap<>();
    }

    // Method to process text from a file and update the frequency maps
    public void processFile(String fileName) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(fileName));
        String line;

        while ((line = reader.readLine()) != null) {
            line = line.replaceAll("[^a-zA-Z]", "").toLowerCase();  // Remove non-letter characters
            calculateCombinations(line);
        }
        reader.close();
    }

    // Method to calculate two-, three-, four-, and five-letter combinations
    private void calculateCombinations(String text) {
        for (int i = 0; i < text.length(); i++) {
            // Two-letter combinations
            if (i < text.length() - 1) {
                String twoLetter = text.substring(i, i + 2);
                twoLetterFreq.put(twoLetter, twoLetterFreq.getOrDefault(twoLetter, 0) + 1);
            }

            // Three-letter combinations
            if (i < text.length() - 2) {
                String threeLetter = text.substring(i, i + 3);
                threeLetterFreq.put(threeLetter, threeLetterFreq.getOrDefault(threeLetter, 0) + 1);
            }

            // Four-letter combinations
            if (i < text.length() - 3) {
                String fourLetter = text.substring(i, i + 4);
                fourLetterFreq.put(fourLetter, fourLetterFreq.getOrDefault(fourLetter, 0) + 1);
            }

            // Five-letter combinations
            if (i < text.length() - 4) {
                String fiveLetter = text.substring(i, i + 5);
                fiveLetterFreq.put(fiveLetter, fiveLetterFreq.getOrDefault(fiveLetter, 0) + 1);
            }
        }
    }

    // Method to create a mapping for compression
    public void createCompressionMap() {
        List<String> emojis = Arrays.asList("😂", "❤️", "🤣", "👍", "😭", "🙏", "😘", "🥰", "😍", "😊", "🎉", "😁", 
                                             "💕", "🥺", "😅", "🔥", "☺️", "🤦", "♥️", "🤷", "🙄", "😆", "🤗", 
                                             "😉", "🎂", "🤔", "👏", "🙂", "😳", "🥳", "😎", "👌", "💜", "😔", 
                                             "💪", "✨", "💖", "👀", "😋", "😏", "😢", "👉", "💗", "😩", "💯", 
                                             "🌹", "💞", "🎈", "💙", "😃", "😡", "💐", "😜", "🙈", "🤞", "😄", 
                                             "🤤", "🙌", "🤪", "❣️", "😀", "💋", "💀", "👇", "💔", "😌", "💓", 
                                             "🤩", "🙃", "😬", "😱", "😴", "🤭", "😐", "🌞", "😒", "😇", "🌸", 
                                             "😈", "🎶", "✌️", "🎊", "🥵", "😞", "💚", "☀️", "🖤", "💰", "😚", 
                                             "👑", "🎁", "💥", "🙋", "☹️", "😑", "🥴", "👈", "💩", "✅", "👋", 
                                             "🤮", "😤", "🤢", "🌟", "❗", "😥", "🌈", "💛", "😝", "😫", "😲", 
                                             "🖕", "‼️", "🔴", "🌻", "🤯", "💃", "👊", "🤬", "🏃", "😕", "👁️", 
                                             "⚡", "☕", "🍀", "💦", "⭐", "🦋", "🤨", "🌺", "😹", "🤘", "🌷", 
                                             "💝", "💤", "🤝", "🐰", "😓", "💘", "🍻", "😟", "😣", "🧐", "😠", 
                                             "🤠", "😻", "🌙", "😛", "🤙", "🙊");

        // Sort frequencies in descending order for all combinations
        List<Map.Entry<String, Integer>> allFrequencies = new ArrayList<>();
        allFrequencies.addAll(fiveLetterFreq.entrySet());
        allFrequencies.addAll(fourLetterFreq.entrySet());
        allFrequencies.addAll(threeLetterFreq.entrySet());
        allFrequencies.addAll(twoLetterFreq.entrySet());

        allFrequencies.sort(Map.Entry.comparingByValue(Comparator.reverseOrder()));

        // Create compression mappings
        for (int i = 0; i < Math.min(allFrequencies.size(), emojis.size()); i++) {
            compressionMap.put(allFrequencies.get(i).getKey(), emojis.get(i));
        }
    }

    // Method to compress the text
    public String compress(String text) {
        String result = text;

        for (Map.Entry<String, String> entry : compressionMap.entrySet()) {
            result = result.replace(entry.getKey(), entry.getValue());
        }

        return result;
    }

    // Method to decompress the text
    public String decompress(String text) {
        String result = text;

        // Reverse the compression map
        Map<String, String> reverseMap = new HashMap<>();
        for (Map.Entry<String, String> entry : compressionMap.entrySet()) {
            reverseMap.put(entry.getValue(), entry.getKey());
        }

        for (Map.Entry<String, String> entry : reverseMap.entrySet()) {
            result = result.replace(entry.getKey(), entry.getValue());
        }

        return result;
    }

    // Method to display letter combinations mapped to emojis
    public void displayEmojiMapping() {
        System.out.println("Emoji to Letter Combination Mapping:");
        for (Map.Entry<String, String> entry : compressionMap.entrySet()) {
            System.out.println(entry.getValue() + " -> " + entry.getKey());
        }
    }

    public static void main(String[] args) {
        LetterCombinationFrequency frequencyCounter = new LetterCombinationFrequency();

        // You can add multiple files to process here
        String[] files = {"FirstInauguralSpeech.txt", "GettysburgAddress.txt", "TheGreatSociety.txt"};

        try {
            for (String file : files) {
                frequencyCounter.processFile(file);
            }

            // Generate the compression and decompression mappings
            frequencyCounter.createCompressionMap();

            // Display the emoji mappings
            frequencyCounter.displayEmojiMapping();

            // Example usage for compression and decompression
            String text = "Fellow-Citizens of the Senate and of the House of Representatives: Among the vicissitudes incident to life no event could have filled me with greater anxieties than that of which the notification was transmitted by your order, and received on the 14th day of the present month. On the one hand, I was summoned by my Country, whose voice I can never hear but with veneration and love, from a retreat which I had chosen with the fondest predilection, and, in my flattering hopes, with an immutable decision, as the asylum of my declining years--a retreat which was rendered every day more necessary as well as more dear to me by the addition of habit to inclination, and of frequent interruptions in my health to the gradual waste committed on it by time. On the other hand, the magnitude and difficulty of the trust to which the voice of my country called me, being sufficient to awaken in the wisest and most experienced of her citizens a distrustful scrutiny into his qualifications, could not but overwhelm with despondence one who (inheriting inferior endowments from nature and unpracticed in the duties of civil administration) ought to be peculiarly conscious of his own deficiencies. In this conflict of emotions all I dare aver is that it has been my faithful study to collect my duty from a just appreciation of every circumstance by which it might be affected. All I dare hope is that if, in executing this task, I have been too much swayed by a grateful remembrance of former instances, or by an affectionate sensibility to this transcendent proof of the confidence of my fellow-citizens, and have thence too little consulted my incapacity as well as disinclination for the weighty and untried cares before me, my error will be palliated by the motives which mislead me, and its consequences be judged by my country with some share of the partiality in which they originated. Such being the impressions under which I have, in obedience to the public summons, repaired to the present station, it would be peculiarly improper to omit in this first official act my fervent supplications to that Almighty Being who rules over the universe, who presides in the councils of nations, and whose providential aids can supply every human defect, that His benediction may consecrate to the liberties and happiness of the people of the United States a Government instituted by themselves for these essential purposes, and may enable every instrument employed in its administration to execute with success the functions allotted to his charge. In tendering this homage to the Great Author of every public and private good, I assure myself that it expresses your sentiments not less than my own, nor those of my fellow- citizens at large less than either. No people can be bound to acknowledge and adore the Invisible Hand which conducts the affairs of men more than those of the United States. Every step by which they have advanced to the character of an independent nation seems to have been distinguished by some token of providential agency; and in the important revolution just accomplished in the system of their united government the tranquil deliberations and voluntary consent of so many distinct communities from which the event has resulted can not be compared with the means by which most governments have been established without some return of pious gratitude, along with an humble anticipation of the future blessings which the past seem to presage. These reflections, arising out of the present crisis, have forced themselves too strongly on my mind to be suppressed. You will join with me, I trust, in thinking that there are none under the influence of which the proceedings of a new and free government can more auspiciously commence. By the article establishing the executive department it is made the duty of the President \"to recommend to your consideration such measures as he shall judge necessary and expedient.\" The circumstances under which I now meet you will acquit me from entering into that subject further than to refer to the great constitutional charter under which you are assembled, and which, in defining your powers, designates the objects to which your attention is to be given. It will be more consistent with those circumstances, and far more congenial with the feelings which actuate me, to substitute, in place of a recommendation of particular measures, the tribute that is due to the talents, the rectitude, and the patriotism which adorn the characters selected to devise and adopt them. In these honorable qualifications I behold the surest pledges that as on one side no local prejudices or attachments, no separate views nor party animosities, will misdirect the comprehensive and equal eye which ought to watch over this great assemblage of communities and interests, so, on another, that the foundation of our national policy will be laid in the pure and immutable principles of private morality, and the preeminence of free government be exemplified by all the attributes which can win the affections of its citizens and command the respect of the world. I dwell on this prospect with every satisfaction which an ardent love for my country can inspire, since there is no truth more thoroughly established than that there exists in the economy and course of nature an indissoluble union between virtue and happiness; between duty and advantage; between the genuine maxims of an honest and magnanimous policy and the solid rewards of public prosperity and felicity; since we ought to be no less persuaded that the propitious smiles of Heaven can never be expected on a nation that disregards the eternal rules of order and right which Heaven itself has ordained; and since the preservation of the sacred fire of liberty and the destiny of the republican model of government are justly considered, perhaps, as deeply, as finally, staked on the experiment entrusted to the hands of the American people. Besides the ordinary objects submitted to your care, it will remain with your judgment to decide how far an exercise of the occasional power delegated by the fifth article of the Constitution is rendered expedient at the present juncture by the nature of objections which have been urged against the system, or by the degree of inquietude which has given birth to them. Instead of undertaking particular recommendations on this subject, in which I could be guided by no lights derived from official opportunities, I shall again give way to my entire confidence in your discernment and pursuit of the public good; for I assure myself that whilst you carefully avoid every alteration which might endanger the benefits of an united and effective government, or which ought to await the future lessons of experience, a reverence for the characteristic rights of freemen and a regard for the public harmony will sufficiently influence your deliberations on the question how far the former can be impregnably fortified or the latter be safely and advantageously promoted. To the foregoing observations I have one to add, which will be most properly addressed to the House of Representatives. It concerns myself, and will therefore be as brief as possible. When I was first honored with a call into the service of my country, then on the eve of an arduous struggle for its liberties, the light in which I contemplated my duty required that I should renounce every pecuniary compensation. From this resolution I have in no instance departed; and being still under the impressions which produced it, I must decline as inapplicable to myself any share in the personal emoluments which may be indispensably included in a permanent provision for the executive department, and must accordingly pray that the pecuniary estimates for the station in which I am placed may during my continuance in it be limited to such actual expenditures as the public good may be thought to require. Having thus imparted to you my sentiments as they have been awakened by the occasion which brings us together, I shall take my present leave; but not without resorting once more to the benign Parent of the Human Race in humble supplication that, since He has been pleased to favor the American people with opportunities for deliberating in perfect tranquillity, and dispositions for deciding with unparalleled unanimity on a form of government for the security of their union and the advancement of their happiness, so His divine blessing may be equally conspicuous in the enlarged views, the temperate consultations, and the wise measures on which the success of this Government must depend.".toLowerCase();
            System.out.println("Original Text: " + text);

            String compressedText = frequencyCounter.compress(text);
            System.out.println("Compressed Text: " + compressedText);
            double compressed = compressedText.length();
            String decompressedText = frequencyCounter.decompress(compressedText);
            double original = text.length();
            System.out.println("Decompressed Text: " + decompressedText);
            System.out.println("Compressed: "+compressed);
            System.out.println("Original: "+original);
            System.out.println("Compression rate: "+(original-compressed)/original);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
