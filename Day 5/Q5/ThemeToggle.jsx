import React, { useState } from 'react';

const ThemeToggle = () => {
  const [isDarkMode, setIsDarkMode] = useState(false);

  const toggleTheme = () => setIsDarkMode(prev => !prev);

  const themeStyles = {
    backgroundColor: isDarkMode ? '#222' : '#f0f0f0',
    color: isDarkMode ? '#fff' : '#000',
    minHeight: '100vh',
    display: 'flex',
    flexDirection: 'column',
    alignItems: 'center',
    justifyContent: 'center',
    transition: 'all 0.3s ease',
  };

  const buttonStyle = {
    padding: '10px 20px',
    fontSize: '1rem',
    border: 'none',
    borderRadius: '8px',
    cursor: 'pointer',
    backgroundColor: isDarkMode ? '#555' : '#ddd',
    color: isDarkMode ? '#fff' : '#000',
    marginTop: '20px',
  };

  return (
    <div style={themeStyles}>
      <h1>{isDarkMode ? 'Dark Theme' : 'Light Theme'}</h1>
      <button onClick={toggleTheme} style={buttonStyle}>
        Toggle Theme
      </button>
    </div>
  );
};

export default ThemeToggle;
