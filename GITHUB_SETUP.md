# Push CodePhoenix AI to GitHub - Step by Step Guide

Complete instructions to upload your CodePhoenix-AI project to GitHub.

---

## Prerequisites

- GitHub account (create one at https://github.com if needed)
- Git installed on your system
- Personal Access Token (PAT) or SSH key configured

---

## Step-by-Step Instructions

### Step 1: Verify Git Installation

Open PowerShell and check if Git is installed:

```powershell
git --version
```

✅ **Expected Output**: `git version 2.x.x...`

If not installed, download from: https://git-scm.com/download/win

---

### Step 2: Create a GitHub Repository

1. Go to https://github.com/new
2. Fill in repository details:
   - **Repository name**: `CodePhoenix-AI` (or your preferred name)
   - **Description**: "Agentic Legacy Modernization Platform"
   - **Visibility**: Choose `Public` (for open source) or `Private` (for closed source)
   - **Initialize this repository with**:
     - ❌ DO NOT check "Add a README file" (we already have one)
     - ❌ DO NOT check "Add .gitignore" (we'll create one)
     - ❌ DO NOT check "Choose a license" (we'll add MIT license)

3. Click **"Create repository"**
4. Copy the repository URL (HTTPS or SSH)
   - **HTTPS**: `https://github.com/yourusername/CodePhoenix-AI.git`
   - **SSH**: `git@github.com:yourusername/CodePhoenix-AI.git`

---

### Step 3: Configure Git (First Time Only)

```powershell
git config --global user.name "Your Name"
git config --global user.email "your.email@example.com"
git config --global core.longpaths true  # Important for Windows with long paths
```

Verify configuration:
```powershell
git config --global --list
```

---

### Step 4: Navigate to Project Directory

```powershell
cd "C:\Users\91789\OneDrive\Desktop\CodePhoenix-AI"
```

Verify you're in the right location:
```powershell
pwd  # Should show: C:\Users\91789\OneDrive\Desktop\CodePhoenix-AI
ls   # Should list: backend, frontend, docs, README.md, etc.
```

---

### Step 5: Create .gitignore File

Create a `.gitignore` file to exclude unnecessary files from Git:

```powershell
# Create .gitignore file
New-Item -Path ".\.gitignore" -ItemType File -Value @"
# Backend - Java/Maven
backend/target/
backend/.classpath
backend/.project
backend/.settings/
backend/*.jar
backend/*.war
backend/bin/

# Backend - IDE
backend/.idea/
backend/.vscode/
backend/*.iml
backend/*.swp
backend/*.swo

# Frontend - Node
frontend/node_modules/
frontend/dist/
frontend/.env.local
frontend/.env.*.local
frontend/*.log
frontend/npm-debug.log*
frontend/yarn-debug.log*
frontend/yarn-error.log*

# Frontend - IDE
frontend/.vscode/
frontend/.idea/
frontend/*.swp
frontend/*.swo

# Database
local_postgres_data/
*.db
*.sqlite

# OS
.DS_Store
Thumbs.db
*.tmp
*.bak

# Environment variables
.env
.env.local
.env.*.local

# Logs
logs/
*.log

# Uploads (generated)
backend/uploads/projects/*
!backend/uploads/projects/.gitkeep

# IDE
.vscode/
.idea/
*.swp
*.swo
*~

# MacOS
.DS_Store
.AppleDouble
.LSOverride

# Windows
Thumbs.db
ehthumbs.db
Desktop.ini

# Build files
*.class
*.jar
*.war
dist/
build/

# Reports
coverage/
reports/
"@ -Encoding UTF8
```

Verify the file was created:
```powershell
cat .\.gitignore
```

---

### Step 6: Create LICENSE File (MIT License)

```powershell
New-Item -Path ".\LICENSE" -ItemType File -Value @"
MIT License

Copyright (c) 2026 CodePhoenix AI

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
"@ -Encoding UTF8
```

---

### Step 7: Initialize Git Repository

```powershell
git init
```

✅ **Expected Output**: `Initialized empty Git repository in C:\Users\91789\OneDrive\Desktop\CodePhoenix-AI\.git\`

---

### Step 8: Add All Files to Staging Area

```powershell
git add .
```

Verify files are staged:
```powershell
git status
```

✅ **Expected Output**: Shows all files in green (ready to commit)

---

### Step 9: Create First Commit

```powershell
git commit -m "Initial commit: Add CodePhoenix-AI project with full documentation"
```

Alternative detailed commit message:
```powershell
git commit -m "Initial commit: CodePhoenix-AI Agentic Legacy Modernization Platform

- Complete backend with Spring Boot 3.5.4
- React frontend with Vite
- PostgreSQL database setup
- Google Gemini AI integration
- Comprehensive README with setup instructions
- Architecture documentation
- MIT License"
```

✅ **Expected Output**: Shows number of files changed, insertions

---

### Step 10: Add Remote Repository

Replace `yourusername` and `repository-url` with your actual GitHub details:

```powershell
git remote add origin https://github.com/yourusername/CodePhoenix-AI.git
```

Or if using SSH:
```powershell
git remote add origin git@github.com:yourusername/CodePhoenix-AI.git
```

Verify remote was added:
```powershell
git remote -v
```

✅ **Expected Output**:
```
origin  https://github.com/yourusername/CodePhoenix-AI.git (fetch)
origin  https://github.com/yourusername/CodePhoenix-AI.git (push)
```

---

### Step 11: Rename Branch to Main (if needed)

```powershell
git branch -M main
```

---

### Step 12: Push to GitHub

For first push, use:
```powershell
git push -u origin main
```

The `-u` flag sets the upstream branch.

**If using HTTPS**, enter your credentials:
- Username: Your GitHub username
- Password: Your Personal Access Token (not your actual password!)
  - Create PAT at: https://github.com/settings/tokens

**If using SSH**, make sure your SSH key is configured.

✅ **Expected Output**:
```
Enumerating objects: ...
Counting objects: 100% ...
Compressing objects: 100% ...
Writing objects: 100% ...
...
* [new branch]      main -> main
Branch 'main' set up to track remote branch 'main' from 'origin'.
```

---

### Step 13: Verify Upload

Go to your GitHub repository: `https://github.com/yourusername/CodePhoenix-AI`

You should see:
- ✅ All files and folders
- ✅ README.md displayed as project description
- ✅ Commit history
- ✅ Branch indicator showing "main"

---

## Complete Command Sequence (Copy & Paste Ready)

If you want to do everything at once:

```powershell
# Navigate to project
cd "C:\Users\91789\OneDrive\Desktop\CodePhoenix-AI"

# Configure Git (first time only)
git config --global user.name "Your Name"
git config --global user.email "your.email@example.com"

# Initialize Git
git init

# Add all files
git add .

# Create commit
git commit -m "Initial commit: CodePhoenix-AI Agentic Legacy Modernization Platform"

# Add remote (replace with your GitHub URL)
git remote add origin https://github.com/yourusername/CodePhoenix-AI.git

# Set main branch and push
git branch -M main
git push -u origin main
```

---

## Troubleshooting

### Error: "Repository already exists"
```powershell
# Remove existing git
Remove-Item -Recurse -Force .git
# Then repeat from Step 7
```

### Error: "fatal: Permission denied (publickey)"
- Use HTTPS instead of SSH
- Or configure SSH key: https://docs.github.com/en/authentication/connecting-to-github-with-ssh

### Error: "fatal: Could not read from remote repository"
```powershell
# Verify remote URL
git remote -v

# If wrong, remove and re-add
git remote remove origin
git remote add origin https://github.com/yourusername/CodePhoenix-AI.git
```

### Error: "The following paths are ignored by one of your .gitignore files"
- Some files matched .gitignore pattern
- This is expected and correct!
- Check `.gitignore` if you want to commit specific ignored files

### Large Files / LFS (Git Large File Storage)
If pushing large files (> 100MB):
```powershell
# Install Git LFS
git lfs install

# Track large files
git lfs track "*.jar"
git lfs track "*.zip"

# Add and push again
git add .gitattributes
git commit -m "Add LFS tracking"
git push origin main
```

---

## After Upload - Next Steps

### 1. Add GitHub Topics
Go to repository → Settings → Topics
Add tags like: `legacy-modernization`, `ai`, `spring-boot`, `react`, `java`

### 2. Add Repository Description
Settings → About → Edit description

### 3. Enable Features
Settings → Enable:
- ✅ Discussions (for community Q&A)
- ✅ Wiki (for additional documentation)
- ✅ Sponsors (to monetize)

### 4. Set up GitHub Pages (Optional)
For hosting documentation:
- Settings → Pages
- Source: Deploy from a branch
- Branch: main → /docs

### 5. Create GitHub Actions (CI/CD)
Create `.github/workflows/build.yml`:
```yaml
name: Build and Test
on: [push, pull_request]
jobs:
  build:
    runs-on: ubuntu-latest
    steps:
      - uses: actions/checkout@v3
      - uses: actions/setup-java@v3
        with:
          java-version: '21'
      - run: cd backend && mvn clean package
```

### 6. Protect Main Branch
Settings → Branches → Add rule:
- Branch name pattern: `main`
- Require pull request reviews before merging
- Require status checks to pass

---

## Future Git Commands (After Initial Setup)

```powershell
# Check status
git status

# See commit history
git log --oneline

# Make changes and commit
git add .
git commit -m "Description of changes"

# Push changes
git push

# Create new branch
git checkout -b feature/new-feature

# Switch branches
git checkout main
git checkout feature/new-feature

# Merge branch
git merge feature/new-feature

# Delete branch
git branch -d feature/new-feature
```

---

## Quick Reference

| Command | Purpose |
|---------|---------|
| `git init` | Initialize repository |
| `git add .` | Stage all changes |
| `git commit -m "msg"` | Commit changes |
| `git remote add origin URL` | Add remote |
| `git push -u origin main` | Push to GitHub |
| `git status` | Check status |
| `git log` | View history |
| `git branch` | List branches |

---

## Resources

- GitHub Documentation: https://docs.github.com
- Git Documentation: https://git-scm.com/doc
- GitHub Help: https://github.com/contact
- Create PAT: https://github.com/settings/tokens
- SSH Setup: https://docs.github.com/en/authentication/connecting-to-github-with-ssh

---

**You're all set! Your CodePhoenix-AI project is now on GitHub! 🚀**

For questions or issues, check the GitHub troubleshooting guide or contact GitHub support.
