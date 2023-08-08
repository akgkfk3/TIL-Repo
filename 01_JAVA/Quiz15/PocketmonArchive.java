package sesac.java.practice.day07.exam22;

import lombok.Getter;
import lombok.Setter;

import java.util.*;

@Getter
@Setter
public class PocketmonArchive {

    protected Map<String, Map<String, String>> pocketmonStatMap;

    protected Map<String, Integer> skillPowerList;

    protected Map<String, List<String>> skillTypeList;

    public PocketmonArchive(String[] skills, int[] skillPower, String[][] skillType,
                            String[] pks, String[] pkType, int[][] pkStat) {

        this.pocketmonStatMap = initPocketmonStatList(pks, pkStat, pkType);
        this.skillPowerList = initSkillPowerList(skills, skillPower);
        this.skillTypeList = initSkillTypeList(pkType, skills, skillType);
    }

    // pocketmonStatMap 초기화 메소드
    private Map<String, Map<String, String>> initPocketmonStatList(String[] pks, int[][] pkStat, String[] pkType) {
        Map<String, Map<String, String>> pocketmonStatMap = new HashMap<>();

        for (int i=0; i<pks.length; i++) {
            Map<String, String> statMap = new HashMap<>();

            statMap.put("hp", String.valueOf(pkStat[i][0]));
            statMap.put("power", String.valueOf(pkStat[i][1]));
            statMap.put("speed", String.valueOf(pkStat[i][2]));
            statMap.put("type", pkType[i]);

            pocketmonStatMap.put(pks[i], statMap);
        }
        return pocketmonStatMap;
    }

    // skillPowerList 초기화 메소드
    private Map<String, Integer> initSkillPowerList(String[] skills, int[] skillPower) {
        Map<String, Integer> skillPowerList = new HashMap<>();

        for (int i=0; i<skills.length; i++)
            skillPowerList.put(skills[i], skillPower[i]);

        return skillPowerList;
    }

    // skillTypeList 초기화 메소드
    private Map<String, List<String>> initSkillTypeList(String[] pkType, String[] skills, String[][] skillType) {
        Map<String, List<String>> skillTypeList = new HashMap<>();

        // pkType 배열을 HashSet으로 변환 --> ex. pkType = {"물", "불", ...}
        Set<String> typeSet = new HashSet<>(Arrays.asList(pkType));

        Iterator<String> it = typeSet.iterator();

        while(it.hasNext()) {
            String type = it.next();

            List<String> skillList = new ArrayList<>();

            for (int i=0; i<skills.length; i++) {
                List<String> list = Arrays.asList(skillType[i]);

                if(list.contains(type))
                    skillList.add(skills[i]);
            }
            skillTypeList.put(type, skillList);
        }
        return skillTypeList;
    }
}
