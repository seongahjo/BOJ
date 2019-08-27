package com.skel.algorithm.question;

public class QWinter2018A implements Question {
    int skillToNumber[] = new int[30];

    public int solution(String skill, String[] skill_trees) {
        int answer = 0;
        for (int i = 0; i < skill.length(); i++) {
            int s = skill.charAt(i) - 'A';
            skillToNumber[s] = i + 1;
        }
        for (int i = 0; i < skill_trees.length; i++) {
            String nowSkill = skill_trees[i];
            boolean isGood = true;
            int before = 0;
            for (int j = 0; j < nowSkill.length() - 1; j++) {
                int num = nowSkill.charAt(j) - 'A';
                if (skillToNumber[num] == 0) continue;
                else if (skillToNumber[num] == before + 1) {
                    before = skillToNumber[num];
                    continue;
                } else {
                    isGood = false;
                    break;
                }
            }
            if (isGood) answer++;
        }
        return answer;
    }


    @Override
    public void run() {
        System.out.println(solution("CBD", new String[]{"BACDE", "CBADF", "AECB", "BDA"}));
    }
}
