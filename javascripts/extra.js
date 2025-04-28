// Fix Material icon display issues
document.addEventListener("DOMContentLoaded", function () {
  // Add :contains selector polyfill for jQuery-like functionality
  // From: https://stackoverflow.com/questions/8714090/queryselectorall-is-not-working-with-contains
  if (!Element.prototype.matches) {
    Element.prototype.matches =
      Element.prototype.msMatchesSelector ||
      Element.prototype.webkitMatchesSelector;
  }

  if (!Element.prototype.closest) {
    Element.prototype.closest = function (s) {
      var el = this;
      do {
        if (el.matches(s)) return el;
        el = el.parentElement || el.parentNode;
      } while (el !== null && el.nodeType === 1);
      return null;
    };
  }

  // Add custom :contains selector
  document.querySelectorAll = (function (querySelectorAll) {
    return function (selector) {
      if (selector.includes(":contains")) {
        const parts = selector.split(":contains");
        const baseSelector = parts[0];
        const searchText = parts[1].replace(/['"()]/g, "").trim();

        const results = [];
        const elements = document.querySelectorAll(baseSelector || "*");

        for (let i = 0; i < elements.length; i++) {
          if (elements[i].textContent.includes(searchText)) {
            results.push(elements[i]);
          }
        }

        return results;
      } else {
        return querySelectorAll.call(this, selector);
      }
    };
  })(document.querySelectorAll);

  // Function to check and fix material icons
  function fixMaterialIcons() {
    // Add direct DOM fixes for known icon issues on specific pages
    document
      .querySelectorAll(
        ".md-content__inner h3, .md-content__inner li, .md-typeset"
      )
      .forEach((element) => {
        if (
          element.innerHTML &&
          element.innerHTML.includes(":material-language-uml:")
        ) {
          element.innerHTML = element.innerHTML.replace(
            /:material-language-uml:/g,
            '<span class="twemoji"><svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24"><path d="M9.5 8.5L11 13l1.5-4.5H14l-2 6h-2l-2-6h1.5m8.5.5a1 1 0 011 1v2h-2v-2a1 1 0 011-1m0-1a2 2 0 00-2 2v2a1 1 0 000 2v2h4v-2a1 1 0 000-2v-2a2 2 0 00-2-2m-1.5-2.09c-.5-.5-1.19-.82-1.91-.82H6a4 4 0 014-4h4a4 4 0 014 4v10a2 2 0 01-2 2H6a2 2 0 01-2-2V7.91c.5.5 1.19.82 1.91.82h8.18c.72 0 1.41-.32 1.91-.82zM14 3H8a2 2 0 00-2 2v8a2 2 0 002 2h8a2 2 0 002-2V5a2 2 0 00-2-2z" /></svg></span>'
          );
        }
      });

    // Find specific problematic elements and fix them
    document
      .querySelectorAll('li:contains("material-language-uml")')
      .forEach((element) => {
        element.innerHTML = element.innerHTML.replace(
          /material-language-uml/g,
          '<span class="twemoji"><svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24"><path d="M9.5 8.5L11 13l1.5-4.5H14l-2 6h-2l-2-6h1.5m8.5.5a1 1 0 011 1v2h-2v-2a1 1 0 011-1m0-1a2 2 0 00-2 2v2a1 1 0 000 2v2h4v-2a1 1 0 000-2v-2a2 2 0 00-2-2m-1.5-2.09c-.5-.5-1.19-.82-1.91-.82H6a4 4 0 014-4h4a4 4 0 014 4v10a2 2 0 01-2 2H6a2 2 0 01-2-2V7.91c.5.5 1.19.82 1.91.82h8.18c.72 0 1.41-.32 1.91-.82zM14 3H8a2 2 0 00-2 2v8a2 2 0 002 2h8a2 2 0 002-2V5a2 2 0 00-2-2z" /></svg></span>'
        );
      });

    // Find all text content that might have misformatted icons
    const elements = document.querySelectorAll("*");

    elements.forEach(function (element) {
      // Skip script and style elements
      if (element.tagName === "SCRIPT" || element.tagName === "STYLE") {
        return;
      }

      // Check text nodes for icon patterns
      for (let i = 0; i < element.childNodes.length; i++) {
        const node = element.childNodes[i];

        if (node.nodeType === Node.TEXT_NODE) {
          // Look for patterns like :material-language-uml:
          const text = node.textContent;
          if (text && text.includes("material-language-uml")) {
            // If we find the pattern, create a proper icon element
            const newHtml = text.replace(
              /:material-language-uml:/g,
              '<span class="twemoji material-language-uml"><svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24"><path d="M9.5 8.5L11 13l1.5-4.5H14l-2 6h-2l-2-6h1.5m8.5.5a1 1 0 011 1v2h-2v-2a1 1 0 011-1m0-1a2 2 0 00-2 2v2a1 1 0 000 2v2h4v-2a1 1 0 000-2v-2a2 2 0 00-2-2m-1.5-2.09c-.5-.5-1.19-.82-1.91-.82H6a4 4 0 014-4h4a4 4 0 014 4v10a2 2 0 01-2 2H6a2 2 0 01-2-2V7.91c.5.5 1.19.82 1.91.82h8.18c.72 0 1.41-.32 1.91-.82zM14 3H8a2 2 0 00-2 2v8a2 2 0 002 2h8a2 2 0 002-2V5a2 2 0 00-2-2z" /></svg></span>'
            );

            // Create a new element with the fixed icon
            const tempDiv = document.createElement("div");
            tempDiv.innerHTML = newHtml;

            // Replace the text node with the new content
            while (tempDiv.firstChild) {
              element.insertBefore(tempDiv.firstChild, node);
            }
            element.removeChild(node);
          }
        }
      }
    });
  }

  // Run the fix immediately
  fixMaterialIcons();

  // Also run after any dynamic content changes
  const observer = new MutationObserver(fixMaterialIcons);
  observer.observe(document.body, { childList: true, subtree: true });
});
