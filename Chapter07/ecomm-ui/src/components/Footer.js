const Footer = () => {
  return (
    <div className="border-t bg-gray-200 border-gray-300">
      <footer className="flex justify-center text-center p-2 text-sm max-w-7xl mx-auto px-4 sm:px-6 lg:px-8">
        &copy; {new Date().getFullYear()} ·{" With"}&nbsp;
        <svg width="20" height="20" fill="#cf6161">
          <path
            fillRule="evenodd"
            clipRule="evenodd"
            d="M3.172 5.172a4 4 0 015.656 0L10 6.343l1.172-1.171a4 4 0 115.656 5.656L10 17.657l-6.828-6.829a4 4 0 010-5.656z"
          />
        </svg>
        &nbsp;{"from"}&nbsp;
        <a
          href="https://github.com/PacktPublishing/Modern-API-Development-with-Spring-and-Spring-Boot"
          className="text-indigo-600 hover:text-indigo-500"
        >
          Modern API development with Spring and Spring Boot
        </a>
      </footer>
    </div>
  );
};

export default Footer;
