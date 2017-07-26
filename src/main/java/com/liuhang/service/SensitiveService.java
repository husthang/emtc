package com.liuhang.service;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by liuhang on 2017/7/26.
 * 关键词过滤
 */
@Service
public class SensitiveService implements InitializingBean {

    private static final String DEFAULT_REPLACEMENT = "***";

    private class TrieNode {
        private boolean end = false;

        private Map<Character, TrieNode> subNodes = new HashMap<>();

        void addSubNode(Character key, TrieNode node) {
            subNodes.put(key, node);
        }

        TrieNode getSubNode(Character key) {
            return subNodes.get(key);
        }

        boolean isEnd() {
            return end;
        }

        void setEed(boolean end) {
            this.end = end;
        }
    }

    @Override
    public void afterPropertiesSet() throws Exception {

        try {
            InputStream inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream("SensitiveWords" +
                    ".txt");//字节流
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);//字节流转换为字符流,桥梁

            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

            String lineText;
            while ((lineText = bufferedReader.readLine()) != null) {
                lineText = lineText.trim();
                addWord(lineText);
            }
        } catch (Exception e) {

        }


    }

    private TrieNode rootNode = new TrieNode();

    //构造字典树，往字典树中加字符
    private void addWord(String lineText) {
        //从字典树的根节点开始
        TrieNode tempNode = rootNode;

        //循环每个字符，加进字典树
        for (int i = 0; i < lineText.length(); i++) {
            Character c = lineText.charAt(i);
            TrieNode node = tempNode.getSubNode(c);
            if (node == null) {
                node = new TrieNode();
                tempNode.addSubNode(c, node);
                tempNode = node;
            } else {
                tempNode = node;
            }
            if (i == lineText.length() - 1) {
                tempNode.setEed(true);
            }
        }
    }

    public String filer(String text) {
        StringBuilder result = new StringBuilder();

        int begin = 0;
        int position = 0;

        TrieNode tempNode = rootNode;

        while (position < text.length()) {
            char c = text.charAt(position);

            tempNode = tempNode.getSubNode(c);

            if (tempNode == null) {//begin开始，不存在敏感词
                result.append(text.charAt(begin));
                begin++;
                position = begin;
                tempNode = rootNode;
            } else if (tempNode.isEnd()) {//发现敏感词
                result.append(DEFAULT_REPLACEMENT);

                begin = position + 1;
                position = begin;
                tempNode = rootNode;
            } else {
                position++;
            }

        }

        result.append(text.substring(begin));

        return result.toString();

    }

    //测试
    public static void main(String[] args) {
        SensitiveService sensitiveService = new SensitiveService();
        sensitiveService.addWord("色情");
        sensitiveService.addWord("习大大");
        System.out.print(sensitiveService.filer("习大大你好污，你这么色情，我要笑哈哈哈"));

    }


}
