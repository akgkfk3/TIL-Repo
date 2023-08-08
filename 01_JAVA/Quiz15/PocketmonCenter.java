package sesac.java.practice.day07.exam22;

import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

@Getter
@Setter
public class PocketmonCenter {

    protected PocketmonArchive pocketmonArchive;

    // 출현 가능한 포켓몬 리스트
    protected String[] pocketmonList = {"파이리", "꼬북이", "이상해씨"};

    protected Random rd = new Random();

    public PocketmonCenter(PocketmonArchive pocketmonArchive) {
        this.pocketmonArchive = pocketmonArchive;
    }

    // 포켓몬 생성 메소드
    public Pocketmon createPocketmon(String name) {
        // 포켓몬 [파이리, 꼬북이, 이상해씨] 중에서 랜덤 선택
        String pocketmonType = choicePocketmon();

        // 빈 포켓몬 객체 생성
        Pocketmon pocketmon = new Pocketmon();

        // 입력받은 포켓몬 이름이 없다면 포켓몬 종류를 포켓몬 이름으로 지정
        if (name.isEmpty())
            pocketmon.setName(pocketmonType);
        else
            pocketmon.setName(name);

        initPocketmonStat(pocketmonArchive, pocketmon, pocketmonType);
        initPocketmonSkill(pocketmonArchive, pocketmon);

        return pocketmon;
    }

    // 포켓몬 스텟 초기화 메소드
    protected void initPocketmonStat(PocketmonArchive pocketmonArchive, Pocketmon pocketmon, String pocketmonType) {
        Map<String, Map<String, String>> pocketmonStatMap = pocketmonArchive.getPocketmonStatMap();
        Map<String, String> statMap = pocketmonStatMap.get(pocketmonType);

        int hp = createRandomInt(Integer.parseInt(statMap.get("hp")));
        int power = createRandomInt(Integer.parseInt(statMap.get("power")));
        int speed = createRandomInt(Integer.parseInt(statMap.get("speed")));
        String type = statMap.get("type");

        Map<String, String> stat = new HashMap<>();

        stat.put("pkName", pocketmonType);
        stat.put("hp", String.valueOf(hp));
        stat.put("power", String.valueOf(power));
        stat.put("speed", String.valueOf(speed));
        stat.put("type", type);

        pocketmon.setHp(hp);
        pocketmon.setStat(stat);
    }

    // 포켓몬 스킬 초기화 메소드
    protected void initPocketmonSkill(PocketmonArchive pocketmonArchive, Pocketmon pocketmon) {
        Map<String, List<String>> skillTypeList = pocketmonArchive.getSkillTypeList();
        Map<String, Integer> skillPowerList = pocketmonArchive.getSkillPowerList();

        Map<String, Integer> skills = new HashMap<>();

        String type = pocketmon.getStat().get("type");
        List<String> typeSkills = skillTypeList.get(type);

        for (int i=0; i<2; i++) {
            int num = rd.nextInt(typeSkills.size());
            String skill = typeSkills.get(num);
            int skillPower = skillPowerList.get(skill);

            if (skills.containsKey(skill)) {
                i--;
                continue;
            }
            skills.put(skill, skillPower);
        }
        pocketmon.setSkill(skills);
    }

    public void fight(Pocketmon myPocketmon, PocketmonCenter pocketmonCenter) throws InterruptedException {

        if(myPocketmon.getHp() == 0) {
            System.out.println("포켓몬을 회복시켜주세요.");
            return;
        }

        Pocketmon enemyPocketmon = createPocketmon("");

        if (pocketmonCenter instanceof GrassGym) {
            System.out.println("풀 관장 " + enemyPocketmon.getStat().get("pkName") + "가 나타났다!");
        } else {
            System.out.println("야생의 " + enemyPocketmon.getStat().get("pkName") + "가 나타났다!");
        }

        // 선제 공격 --> 1이면 내 포켓몬이 먼저 공격, 2이면 상대 포켓몬이 먼저 공격
        int myPocketmonSpeed = Integer.parseInt(myPocketmon.getStat().get("speed"));
        int enemyPocketmonSpeed = Integer.parseInt(enemyPocketmon.getStat().get("speed"));
        int attackOrder = (myPocketmonSpeed > enemyPocketmonSpeed) ? 1 : 2;

        // 내 포켓몬, 상대 포켓몬의 스킬 목록 (Map --> String[] 변환 for 랜덤)
        String[] myPocketmonSkills = myPocketmon.getSkill().keySet().toArray(new String[0]);
        String[] enemyPocketmonSkills = enemyPocketmon.getSkill().keySet().toArray(new String[0]);

        while(true) {
            switch(attackOrder) {
                case 1:
                    attack(myPocketmon, myPocketmonSkills, enemyPocketmon, enemyPocketmonSkills);
                    if (enemyPocketmon.getHp() <= 0) {
                        enemyPocketmon.setHp(0);
                        System.out.println(myPocketmon.getName() + "의 승리!");
                        return;
                    }

                    attack(enemyPocketmon, enemyPocketmonSkills, myPocketmon, myPocketmonSkills);
                    if (myPocketmon.getHp() <= 0) {
                        myPocketmon.setHp(0);
                        System.out.println(enemyPocketmon.getName() + "의 승리!");
                        return;
                    }
                    break;
                case 2:
                    attack(enemyPocketmon, enemyPocketmonSkills, myPocketmon, myPocketmonSkills);
                    if (myPocketmon.getHp() <= 0) {
                        myPocketmon.setHp(0);
                        System.out.println(enemyPocketmon.getName() + "의 승리!");
                        return;
                    }

                    attack(myPocketmon, myPocketmonSkills, enemyPocketmon, enemyPocketmonSkills);
                    if (enemyPocketmon.getHp() <= 0) {
                        enemyPocketmon.setHp(0);
                        System.out.println(myPocketmon.getName() + "의 승리!");
                        return;
                    }
                    break;
            }
        }
    }

    protected void attack(Pocketmon p1, String[] p1_Skills, Pocketmon p2, String[] p2_skill) throws InterruptedException {

        String p1_Skill = p1_Skills[rd.nextInt(2)];
        System.out.println(p1.getName() + "의 " +
                p1_Skill + " 공격!");

        // p1 공격력 (Power), 스킬 공격력 (SkillPower) --> 데미지 계산
        int p1_SkillPower = p1.getSkill().get(p1_Skill);
        int p1_Power = Integer.parseInt(p1.getStat().get("power"));
        int damage = (int) ((p1_SkillPower * (p1_Power * 0.01)) * 0.5);
        
        // p2 HP 수정
        int p2_HP = p2.getHp() - damage;
        System.out.println(p2.getName() + "은 " + damage + "의 데미지를 받았다!!");
        p2.setHp(p2_HP);
        Thread.sleep(1000);
    }

    public void heal(Pocketmon pocketmon) {

        int maxHP = Integer.parseInt(pocketmon.getStat().get("hp"));
        int hp = pocketmon.getHp();

        if (hp == maxHP) {
            System.out.println("이미 풀 HP 입니다.");
            return;
        }
        System.out.println("회복전 HP : " + pocketmon.getHp());
        pocketmon.setHp(maxHP);
        System.out.println("회복후 HP : " + pocketmon.getHp());
        System.out.println("포켓몬이 회복되었습니다.");
    }

    protected String choicePocketmon() {
        return pocketmonList[rd.nextInt(3)];
    }

    protected int createRandomInt(int num) {
        int check = rd.nextInt(2);

        if (check == 1) {
            num += rd.nextInt(11);
        } else {
            num -= rd.nextInt(11);
        }
        return num;
    }
}
