package com.kuldeep.algorithims;

import com.kuldeep.algorithims.LeftViewBinaryTree.TreeNode;
import org.junit.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class LeftViewBinaryTreeTest {

    @Test
    public void shouldProduceLeftViewOfBinaryTree() {
        TreeNode root =
                TreeNode.of(10)
                        .addLeftChild(
                                TreeNode.of(11)
                                        .addLeftChild(
                                                TreeNode.of(5)
                                        )
                                        .addRightChild(
                                                TreeNode.of(7)
                                                        .addLeftChild(
                                                                TreeNode.of(23)
                                                                        .addLeftChild(
                                                                                TreeNode.of(-1)
                                                                                        .addLeftChild(TreeNode.of(3))
                                                                        )
                                                        )
                                        )
                        ).addRightChild(
                                TreeNode.of(12)
                                        .addLeftChild(
                                                TreeNode.of(15)
                                        )
                        );

        List<Integer> actualLeftView = new LeftViewBinaryTree().leftView(root);

        List<Integer> expectedLeftView = List.of(10, 11, 5, 23, -1, 3);

        assertThat(actualLeftView)
                .containsExactlyElementsOf(expectedLeftView);
    }

}