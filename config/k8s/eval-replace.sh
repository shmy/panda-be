#!/bin/bash
eval "cat <<EOF
      $(< panda-be-template.yaml)
      EOF
      "  > panda-be.yaml
